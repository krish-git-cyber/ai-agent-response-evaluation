# Identified Bugs – AI Agent Response Evaluation

This document records defects identified during development and testing of the response evaluator.

## BUG-001: Sensitive Information Detection Failure

**Severity:** Critical  
**Category:** Security Testing

**Issue:**  
The evaluator initially failed to detect certain requests for passwords and verification codes because of inconsistent text casing and phrase matching.

**Expected Result:**  
Requests for sensitive account information should always be rejected.

**Resolution:**  
Standardised text using lowercase conversion with `Locale.ROOT` and corrected the matching phrases.

**Regression Testing:**  
Added tests for password and verification-code requests, including parameterized test cases.

**Status:** Fixed.

---

## BUG-002: Unsupported Payment Success Claim Accepted

**Severity:** High  
**Category:** Accuracy Testing

**Issue:**  
The evaluator initially failed to reject certain payment-success claims when transaction evidence was unavailable.

**Expected Result:**  
A payment-success claim must be rejected when transaction evidence is absent.

**Resolution:**  
Corrected payment-question detection and payment-success phrase matching.

**Regression Testing:**  
Added tests for payment-success claims with and without transaction evidence, including alternative wording.

**Status:** Fixed.

---

## BUG-003: Relevant Support Escalation Rejected

**Severity:** Medium  
**Category:** Functional Testing

**Issue:**  
The relevance rule rejected a valid support-escalation response because it did not contain the expected account-related keywords.

**Expected Result:**  
A suitable recommendation to contact customer support should be accepted when recovery has failed.

**Resolution:**  
Updated the relevance rule to recognise support-related responses.

**Regression Testing:**  
Verified that the failed-recovery-with-support test passes.

**Status:** Fixed.

---

## BUG-004: Irrelevant Responses Not Properly Evaluated

**Severity:** Medium  
**Category:** Negative Testing

**Issue:**  
The evaluator needed additional negative test coverage to verify that unrelated answers are rejected.

**Expected Result:**  
A response about account login should not pass as an answer to a payment question, and unrelated content should not pass as an account-support answer.

**Resolution:**  
Added explicit relevance checks and negative test cases.

**Regression Testing:**  
Added tests for irrelevant payment and account responses.

**Status:** Addressed and covered by automated tests.

---

## Summary

Four development issues were documented and addressed through code corrections and additional automated tests.

The current test suite contains 22 successful test executions with zero failures and zero errors.