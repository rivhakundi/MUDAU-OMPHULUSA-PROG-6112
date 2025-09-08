import java.util.ArrayList;
import java.util.Scanner;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;

// SeriesModel class to hold series data
class SeriesModel {
    public String seriesId;
    public String seriesName;
    public String seriesAge;
    public String seriesNumberOfEpisodes;
}

// Series class with all the required methods
@SupportedSourceVersion(SourceVersion.RELEASE_23)
class JavaApplication8 {
    private ArrayList<SeriesModel> seriesList;
    
    public JavaApplication8() {
        seriesList = new ArrayList<>();
    }
    
    public void captureSeries(String id, String name, String age, String episodes) {
        SeriesModel newSeries = new SeriesModel();
        newSeries.seriesId = id;
        newSeries.seriesName = name;
        newSeries.seriesAge = age;
        newSeries.seriesNumberOfEpisodes = episodes;
        seriesList.add(newSeries);
    }
    
    public SeriesModel searchSeries(String id) {
        for (SeriesModel series : seriesList) {
            if (series.seriesId.equals(id)) {
                return series;
            }
        }
        return null;
    }
    
    public boolean updateSeries(String id, String name, String age, String episodes) {
        for (SeriesModel series : seriesList) {
            if (series.seriesId.equals(id)) {
                series.seriesName = name;
                series.seriesAge = age;
                series.seriesNumberOfEpisodes = episodes;
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteSeries(String id) {
        for (int i = 0; i < seriesList.size(); i++) {
            if (seriesList.get(i).seriesId.equals(id)) {
                seriesList.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<SeriesModel> getSeriesList() {
        return seriesList;
    }
    
    public boolean validateAgeRestriction(String age) {
        try {
            int ageValue = Integer.parseInt(age);
            return ageValue >= 2 && ageValue <= 18;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }
}

