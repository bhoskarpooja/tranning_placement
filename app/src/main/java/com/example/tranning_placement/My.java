package com.example.tranning_placement;

public class My {

    String companyname,description,location,role,imageurl;

    public My() {
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public My(String companyname, String description, String role, String location, String imageurl) {
        this.companyname = companyname;
        this.description = description;
        this.role = role;
        this.location = location;
        this.imageurl = imageurl;
    }
}
