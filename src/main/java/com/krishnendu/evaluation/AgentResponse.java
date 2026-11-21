package com.krishnendu.evaluation;

public record AgentResponse(
        String customerMessage, // What the customer asked
        String response, // What the AI agent answered
        boolean hasTransactionEvidence // Whether transaction records are available to support claims
) {
}
