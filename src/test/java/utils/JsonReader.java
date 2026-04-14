package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonReader {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, Object> readJsonFile(String filePath) throws IOException {
        return objectMapper.readValue(new File(filePath), new TypeReference<LinkedHashMap<String, Object>>() {
        });
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getGridExpectedResults(String filePath, List<Map<String, String>> filters) throws IOException {
        Map<String, Object> root = readJsonFile(filePath);
        Map<String, Object> gridFiltering = (Map<String, Object>) root.get("gridFiltering");

        if (gridFiltering == null) {
            throw new IllegalArgumentException("Missing 'gridFiltering' in JSON: " + filePath);
        }

        for (Object scenarioValue : gridFiltering.values()) {
            Map<String, Object> scenario = (Map<String, Object>) scenarioValue;
            List<Map<String, Object>> jsonFilters = (List<Map<String, Object>>) scenario.get("filters");

            if (filtersMatch(filters, jsonFilters)) {
                return (List<Map<String, Object>>) scenario.get("expectedResults");
            }
        }

        throw new IllegalArgumentException("No matching gridFiltering scenario found in JSON for filters: " + filters);
    }

    private boolean filtersMatch(List<Map<String, String>> stepFilters, List<Map<String, Object>> jsonFilters) {
        if (stepFilters == null || jsonFilters == null || stepFilters.size() != jsonFilters.size()) {
            return false;
        }

        for (int i = 0; i < stepFilters.size(); i++) {
            Map<String, String> stepFilter = stepFilters.get(i);
            Map<String, Object> jsonFilter = jsonFilters.get(i);

            String stepField = String.valueOf(stepFilter.get("field")).trim();
            String stepValue = String.valueOf(stepFilter.get("value")).trim();
            String jsonField = String.valueOf(jsonFilter.get("field")).trim();
            String jsonValue = String.valueOf(jsonFilter.get("value")).trim();

            if (!stepField.equalsIgnoreCase(jsonField) || !stepValue.equalsIgnoreCase(jsonValue)) {
                return false;
            }
        }

        return true;
    }
}
