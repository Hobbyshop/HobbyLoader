package me.hobbyshop.hobbyloader.api.mod;

import lombok.Data;

@Data
public class ModInfo {

    private String name;
    private String description = "No description set :(";
    private String author = "No author set :(";
    private String entry;

}
