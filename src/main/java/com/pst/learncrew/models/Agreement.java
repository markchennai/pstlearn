package com.pst.learncrew.models;

import com.pst.learncrew.enums.PeriodEnum;
import com.pst.learncrew.enums.RateEnum;
import com.pst.learncrew.enums.TypeEnum;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;

@Data
public class Agreement implements Serializable {

  /** Serial Version UID. */
  private static final long serialVersionUID = 1L;

  @NotNull(message = "ERR400_REQUIRED")
  private String id;

  @NotNull(message = "ERR400_REQUIRED")
  private TypeEnum type;

  private String accountingProfileOverride;
  private RateEnum rate;
  private PeriodEnum period;
}
