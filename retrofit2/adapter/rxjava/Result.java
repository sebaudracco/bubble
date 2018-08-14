package retrofit2.adapter.rxjava;

import retrofit2.Response;

public final class Result<T> {
    private final Throwable error;
    private final Response<T> response;

    public static <T> Result<T> error(Throwable error) {
        if (error != null) {
            return new Result(null, error);
        }
        throw new NullPointerException("error == null");
    }

    public static <T> Result<T> response(Response<T> response) {
        if (response != null) {
            return new Result(response, null);
        }
        throw new NullPointerException("response == null");
    }

    private Result(Response<T> response, Throwable error) {
        this.response = response;
        this.error = error;
    }

    public Response<T> response() {
        return this.response;
    }

    public Throwable error() {
        return this.error;
    }

    public boolean isError() {
        return this.error != null;
    }

    public String toString() {
        if (this.error != null) {
            return "Result{isError=true, error=\"" + this.error + "\"}";
        }
        return "Result{isError=false, response=" + this.response + '}';
    }
}
