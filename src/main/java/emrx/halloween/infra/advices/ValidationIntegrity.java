package emrx.halloween.infra.advices;

public class ValidationIntegrity extends RuntimeException {
  
  public ValidationIntegrity(String message) {
    super(message);
}
}
