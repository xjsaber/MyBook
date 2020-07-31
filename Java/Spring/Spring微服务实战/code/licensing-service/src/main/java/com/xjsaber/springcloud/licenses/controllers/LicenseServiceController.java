package com.xjsaber.springcloud.licenses.controllers;

import com.xjsaber.springcloud.licenses.model.License;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

    public License getLicenses(@PathVariable("organizationId") String organizationId, @PathVariable("licensesId") String licensesId) {
        return new License()
                .withLicenseId(licensesId)
                .withLicenseType("Teleco")
                .withProductName("Seat")
                .withOrganizationId(organizationId);

    }
}
