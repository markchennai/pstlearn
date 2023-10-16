package com.pst.learncrew.controllers;

import com.pst.learncrew.business.ProfilesBusiness;
import com.pst.learncrew.enums.AgreementTypeEnum;
import com.pst.learncrew.models.Profile;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crewpro/timekeeping/guarantee")
public class ProfilesController {

  @Autowired ProfilesBusiness profilesBusiness;

  @GetMapping(
      value = "/profiles",
      produces = {"application/json"})
  public ResponseEntity<List<Profile>> getProfiles(
      @RequestParam(name = "agreementType", required = false) AgreementTypeEnum agreementType,
      @RequestParam(name = "excludeRestDaysFlag", required = false) Boolean excludeRestDaysFlag,
      @RequestParam(name = "commentId", required = false) String commentId,
      @RequestHeader Map<String, String> headers) {

    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Correlation-Key", "Value-Correlation-Key");
    responseHeaders.set("Location", "Value-Location");

    return new ResponseEntity<List<Profile>>(
        profilesBusiness.getProfiles(agreementType, excludeRestDaysFlag, commentId),
        responseHeaders,
        HttpStatus.OK);
  }

  @PostMapping(
      value = "/profiles",
      produces = {"application/json"})
  public ResponseEntity<Profile> createAgreementProfile(
      @Valid @RequestBody Profile profile, @RequestHeader Map<String, String> headers) {

    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Correlation-Key", "Value-Correlation-Key");
    responseHeaders.set("Location", "Value-Location");

    return new ResponseEntity<Profile>(
        profilesBusiness.createAgreementProfile(profile), responseHeaders, HttpStatus.CREATED);
  }

  @GetMapping(
      value = "/profiles/{oId}",
      produces = {"application/json"})
  public ResponseEntity<Profile> getProfileById(
      @PathVariable Integer oId, @RequestHeader Map<String, String> headers) {

    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Correlation-Key", "Value-Correlation-Key");
    responseHeaders.set("Location", "Value-Location");

    return new ResponseEntity<Profile>(
        profilesBusiness.getProfileById(oId), responseHeaders, HttpStatus.OK);
  }

  @PutMapping(
      value = "/profiles/{oId}",
      produces = {"application/json"})
  public ResponseEntity<Profile> updateProfile(
      @PathVariable Integer oId,
      @Valid @RequestBody Profile profile,
      @RequestHeader Map<String, String> headers) {

    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Correlation-Key", "Value-Correlation-Key");
    responseHeaders.set("Location", "Value-Location");

    return new ResponseEntity<Profile>(
        profilesBusiness.updateProfile(oId, profile), responseHeaders, HttpStatus.OK);
  }

  @DeleteMapping(
      value = "/profiles/{oId}",
      produces = {"*/*"})
  public ResponseEntity<Void> deleteProfile(
      @PathVariable Integer oId, @RequestHeader Map<String, String> headers) {

    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Correlation-Key", "Value-Correlation-Key");

    profilesBusiness.deleteProfile(oId);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }
}
