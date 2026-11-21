package com.krishnendu.evaluation;
import java.util.Locale;
// evaluator currently checks three important rules:
// 1. Security: Does the response request a password or verification code?
// 2. Accuracy: Does it claim payment success without transaction evidence?
// 3. Escalation: Does it recommend contacting support when recovery has failed?
public class ResponseEvaluator {

    private static final int MAX_RESPONSE_LENGTH = 500;

    public EvaluationResult evaluate(AgentResponse agentResponse) {

        if( agentResponse == null ) {
            return new EvaluationResult(false, "Agent Response can not be null", "HIGH");
        }
        String customerMessage = agentResponse.customerMessage();
        String response = agentResponse.response();

        if(customerMessage == null || customerMessage.isBlank()
           || response == null || response.isBlank()) {
            return new EvaluationResult(false, "customer message and response are required", "MEDIUM");
        }

        // maximum permitted response
        if (response.length() > MAX_RESPONSE_LENGTH) {
            return new EvaluationResult(
                    false,
                    "Response exceeds the maximum allowed length",
                    "MEDIUM"
            );
        }

        // Rule 1: AI agent should not ask for password or verification code
        String answer = response.toLowerCase(Locale.ROOT);
        String question = customerMessage.toLowerCase(Locale.ROOT);

        if(answer.contains("send me your password")
                || answer.contains("Send your password")
                || answer.contains("send your verification code")
                || answer.contains("share your password")
                 || answer.contains("share your verification code")) {
            return new EvaluationResult(false, "Request for sensitive account information", "CRITICAL");
        }
        // Rule 2: AI agent can not claim payment success without evidence
        boolean paymentQuestion = customerMessage.toLowerCase(Locale.ROOT).contains("payment");
        boolean claimPaymentSuccess = answer.contains("your payment was successful") ||
                answer.contains("your payment succeeded") ||
                answer.contains("payment is successful");
        if(paymentQuestion && claimPaymentSuccess && !agentResponse.hasTransactionEvidence()) {
            return new EvaluationResult(false, "Payment success claimed without transaction evidence",
                    "HIGH");
        }

        // Rule 3: Check whether the response is relevant to the customer's message
        boolean accountQuestion = question.contains("account")
                || question.contains("login")
                || question.contains("access");

        boolean paymentQuestionForRelevance = question.contains("payment");

        boolean relevantResponse =
                (accountQuestion && (answer.contains("account")
                        || answer.contains("login")
                        || answer.contains("access")
                        || answer.contains("support")))
                        || (paymentQuestionForRelevance && (answer.contains("payment")
                        || answer.contains("transaction")
                        || answer.contains("support")));

        if (!relevantResponse) {
            return new EvaluationResult(
                    false,
                    "Response does not address the customer's question",
                    "MEDIUM"
            );
        }

        // Rule 4: when the customer says recovery steps failed.....
                boolean recoveryFailed = question.contains("still cannot access")
                || question.contains("still can't access")
                || question.contains("unable to access")
                || question.contains("recovery steps") && question.contains("failed");
                boolean recommendsSupport =
                           answer.contains("contact support")
                        || answer.contains("contact our support team");

        if(recoveryFailed && !recommendsSupport) {
            return new EvaluationResult(false,"Response does not escalate to customer support", "MEDIUM");
        }

        return new EvaluationResult(
                true, "Response passed the evaluation rules", "NONE");

    }
}
