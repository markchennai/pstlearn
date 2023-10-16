package com.pst.learncrew.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class ErrorsDto implements Serializable {

  /** Serial Version UID. */
  private static final long serialVersionUID = 1L;

  private List<Error> errors;
}
