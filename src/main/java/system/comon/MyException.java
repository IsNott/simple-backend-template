package system.comon;

/**
 * @author Nott
 * @Date 2023/2/17
 */


public class MyException extends Exception {
    private String msg;
    private Long retcode;
    private Object retbo;


    public Long getRetcode() {
        return retcode;
    }

    public void setRetcode(Long retcode) {
        this.retcode = retcode;
    }


    public MyException(String msg){
        this.msg = msg;
        this.retcode = -999L;
    }


    public MyException(String msg,Long retcode){
        super(msg);
        this.msg = msg;
        this.retcode = retcode;
    }


}
