package com.krishnendu.evaluation;

public record EvaluationResult(
        boolean passed, // Whether the AI response passed evaluation
        String reason, // Why the response passed or failed
        String severity // How serious the detected problem is
) {
}
