package com.xyz.model;

public class ResponseEntity {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static final Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final ResponseEntity resp = new ResponseEntity();

        public Builder message(String message) {
            resp.message = message;
            return this;
        }

        public ResponseEntity build() {
            return resp;
        }
    }

}
