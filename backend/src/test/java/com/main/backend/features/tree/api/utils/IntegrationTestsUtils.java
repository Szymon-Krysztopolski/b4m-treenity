package com.main.backend.features.tree.api.utils;

import com.main.backend.features.tree.dto.TreeDTO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class IntegrationTestsUtils {

    public String getIdFromResponse(String input) {
        Matcher matcher = Pattern.compile("\\{([^}]*)\\}").matcher(input);
        return (matcher.find() ? matcher.group(1) : "");
    }

    public String getEdgeId(String source, String destination) {
        return String.format("edge---%s::%s", source, destination);
    }

    public boolean checkIfNodeExists(TreeDTO tree, String id, String type, String data) {
        return tree.checkIfNodeExists(id, type, data);
    }

    public boolean checkIfEdgeExists(TreeDTO tree, String source, String destination, int label) {
        return tree.checkIfEdgeExists(getEdgeId(source, destination), source, destination, label);
    }

    public boolean checkIfNodeExists(TreeDTO tree, String id) {
        return tree.checkIfNodeExists(id);
    }

    public boolean checkIfEdgeExists(TreeDTO tree, String id) {
        return tree.checkIfEdgeExists(id);
    }
}
