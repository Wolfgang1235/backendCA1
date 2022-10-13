
package errorhandling;

public class ExceptionDTO {
    private final int code;
    private final String message;

  public ExceptionDTO(int code, String description){
      this.code = code;
      this.message = description;
  }
    
}
