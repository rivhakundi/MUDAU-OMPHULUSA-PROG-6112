public class SeriesTest {
 private JavaApplication8 seriesManager;
    
    
    public void setUp() {
        seriesManager = new JavaApplication8();
        // Add some test data
        seriesManager.captureSeries("101", "Extreme Sports", "12", "10");
        seriesManager.captureSeries("102", "Home Cooking", "10", "15");
    }
    
    
    public void testSearchSeries() {
        SeriesModel result = seriesManager.searchSeries("101");
        assertNotNull(result);
        assertEquals("101", result.seriesId);
        assertEquals("Extreme Sports", result.seriesName);
        assertEquals("12", result.seriesAge);
        assertEquals("10", result.seriesNumberOfEpisodes);
    }
    
    
    public void testSearchSeries_SeriesNotFound() {
        SeriesModel result = seriesManager.searchSeries("999");
        assertNull(result);
    }
    
    
    public void testUpdateSeries() {
        boolean result = seriesManager.updateSeries("101", "Extreme Sports 2025", "14", "12");
        assertTrue(result);
        
        SeriesModel updatedSeries = seriesManager.searchSeries("101");
        assertNotNull(updatedSeries);
        assertEquals("Extreme Sports 2025", updatedSeries.seriesName);
        assertEquals("14", updatedSeries.seriesAge);
        assertEquals("12", updatedSeries.seriesNumberOfEpisodes);
    }
    
    
    public void testDeleteSeries() {
        boolean result = seriesManager.deleteSeries("101");
        assertTrue(result);
        
        SeriesModel deletedSeries = seriesManager.searchSeries("101");
        assertNull(deletedSeries);
    }
    
    
    public void testDeleteSeries_SeriesNotFound() {
        boolean result = seriesManager.deleteSeries("999");
        assertFalse(result);
    }
    
    
    public void testSeriesAgeRestriction_AgeValid() {
        assertTrue(seriesManager.validateAgeRestriction("5"));
        assertTrue(seriesManager.validateAgeRestriction("12"));
        assertTrue(seriesManager.validateAgeRestriction("18"));
    }
    
    
    public void testSeriesAgeRestriction_SeriesAgeInValid() {
        assertFalse(seriesManager.validateAgeRestriction("1"));
        assertFalse(seriesManager.validateAgeRestriction("19"));
        assertFalse(seriesManager.validateAgeRestriction("0"));
        assertFalse(seriesManager.validateAgeRestriction("twenty"));
        assertFalse(seriesManager.validateAgeRestriction(""));
    }

        private void assertFalse(boolean validateAgeRestriction) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void assertTrue(boolean validateAgeRestriction) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void assertNull(SeriesModel deletedSeries) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void assertNotNull(SeriesModel updatedSeries) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void assertEquals(String extreme_Sports_2025, String seriesName) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
    