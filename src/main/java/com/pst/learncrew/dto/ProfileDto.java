package com.pst.learncrew.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;

@Data
public class ProfileDto implements Serializable {

  /** Serial Version UID. */
  private static final long serialVersionUID = 1L;

  private Integer oId;

  @NotNull(message = "ERR400_REQUIRED")
  private String profileId;

  @NotNull(message = "ERR400_REQUIRED")
  private String description;

  private String createdTimestamp;
  private String createdUserId;
  private String lastUpdatedTimestamp;
  private String lastUpdatedUserId;
  private CommentDto commentDto;
  private AgreementDto agreementDto;
  private ReductionDto reductionDto;
}
