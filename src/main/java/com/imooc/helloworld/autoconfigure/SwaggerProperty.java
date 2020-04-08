package com.imooc.helloworld.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;

/**
 * @author Yu
 * @since 2020/3/28
 */
@ConfigurationProperties(prefix = "hello.swagger")
public class SwaggerProperty {

    /**
     * Api title
     */
    private String title = "Application API";

    /**
     * Api description
     */
    private String description = "API DOCUMENT";

    /**
     * Api version
     */
    private String version = "0.0.1-SNAPSHOT";

    /**
     * about how to contact
     */
    private Contact contact;

    /**
     * terms of service url
     */
    private String termsOfServiceUrl;

    /**
     * api license
     */
    private String license;

    /**
     * license url
     */
    private String licenseUrl;

    private SimpleVendorExtension[] vendorExtensions;

    private boolean enableUrlTemplating = false;

    /**
     * Externally control auto initialization of this swagger plugin instance.
     */
    private boolean enable = true;

    /**
     * filter interface
     */
    private String basePackages;

    private String[] paths;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getTitle() {
        return StringUtils.isEmpty(this.title) ? ApiInfo.DEFAULT.getTitle() : this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return StringUtils.isEmpty(this.description) ? ApiInfo.DEFAULT.getDescription() : this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return StringUtils.isEmpty(this.version) ? ApiInfo.DEFAULT.getVersion() : this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Contact getContact() {
        return contact==null ? Contact.DEFAULT_CONTACT : contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getTermsOfServiceUrl() {
        return StringUtils.isEmpty(this.termsOfServiceUrl) ? ApiInfo.DEFAULT.getTermsOfServiceUrl() : this.termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public String getLicense() {
        return StringUtils.isEmpty(this.license) ? ApiInfo.DEFAULT.getLicense() : this.license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicenseUrl() {
        return StringUtils.isEmpty(this.licenseUrl) ? ApiInfo.DEFAULT.getLicenseUrl() : this.licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public SimpleVendorExtension[] getVendorExtensions() {
        return this.vendorExtensions == null ? new SimpleVendorExtension[0] : this.vendorExtensions;
    }

    public void setVendorExtensions(SimpleVendorExtension[] vendorExtensions) {
        this.vendorExtensions = vendorExtensions;
    }

    public boolean isEnableUrlTemplating() {
        return enableUrlTemplating;
    }

    public void setEnableUrlTemplating(boolean enableUrlTemplating) {
        this.enableUrlTemplating = enableUrlTemplating;
    }

    public String getBasePackages() {
        return this.basePackages;
    }

    public void setBasePackages(String basePackages) {
        this.basePackages = basePackages;
    }

    public String[] getPaths() {
        return paths == null ? new String[0] : this.paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }

    private static class SimpleVendorExtension implements VendorExtension<String> {

        private String name;
        private String value;

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public SimpleVendorExtension() {

        }

        public SimpleVendorExtension(String name, String value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public String getValue() {
            return null;
        }
    }

    public static class Contact{

        public static final Contact DEFAULT_CONTACT = new Contact("", "","");
        private springfox.documentation.service.Contact  contact;
        private String name;
        private String url;
        private String email;

        public Contact(String name, String url, String email) {
            this.name = name;
            this.url = url;
            this.email = email;
        }

        public Contact(){}

        public void setName(String name) {
            this.name = name;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }

        public String getEmail() {
            return email;
        }

        public springfox.documentation.service.Contact getContact(){
            return new springfox.documentation.service.Contact(this.name, this.url, this.email);
        }
    }
}
