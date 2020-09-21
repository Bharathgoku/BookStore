package com.bookstore.exceptions;

public class CatalogNotFoundException extends RuntimeException {
  public CatalogNotFoundException() {}

  public CatalogNotFoundException(String message) {
    super(message);
  }

  public CatalogNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public CatalogNotFoundException(Throwable cause) {
    super(cause);
  }

}
