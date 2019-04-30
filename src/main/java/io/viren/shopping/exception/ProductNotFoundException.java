package io.viren.shopping.exception;

public class ProductNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -3328601315169011444L;

  private String code;

  public ProductNotFoundException(final String code) {
    this.code = code;
  }

  @Override
  public String getMessage() {
    return String.format("No product found for code %s", code);
  }
}
