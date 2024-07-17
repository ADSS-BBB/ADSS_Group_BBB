package DeliveryM.ServiceLayer;

public class Response {

    private String ErrorMessage;
    private Object ReturnValue;
    public Response(){}
    public Response(String errorMessage,Object returnValue){
        this.ErrorMessage=errorMessage;
        this.ReturnValue=returnValue;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public void setReturnValue(Object returnValue) {
        ReturnValue = returnValue;
    }

    public Object getReturnValue() {
        return ReturnValue;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }


}
