package utilClass;

public class IdsMetaData {

	private Long realId;
	private Long todayId;
	
	// Constructeur
    public IdsMetaData(Long realId, Long todayId) {
        this.realId = realId;
        this.todayId = todayId;
    }
    
    // Getters
    public Long getRealId() {
        return realId;
    }

    public Long getTodayId() {
        return todayId;
    }

    // Setters
    public void setRealId(Long realId) {
        this.realId = realId;
    }

    public void setTodayId(Long todayId) {
        this.todayId = todayId;
    }

    @Override
    public String toString() {
        return "realId: " + realId + ", todayId: " + todayId;
    }
	
}
