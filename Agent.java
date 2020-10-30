package agentdisplay;

public class Agent {
    private int AgentId;
    private String AgtFirstName;
    private String AgtMiddleName;
    private String AgtLastName;
    private String BusPhone;
    private String AgtEmail;
    private String AgtPosition;
    private int AgencyId;

    public Agent(int agentId, String agtFirstName, String agtMiddleName, String agtLastName, String busPhone, String agtEmail, String agtPosition, int agencyId) {
        AgentId = agentId;
        AgtFirstName = agtFirstName;
        AgtMiddleName = agtMiddleName;
        AgtLastName = agtLastName;
        BusPhone = busPhone;
        AgtEmail = agtEmail;
        AgtPosition = agtPosition;
        AgencyId = agencyId;
    }

    public int getAgentId() {
        return AgentId;
    }

    public void setAgentId(int agentId) {
        AgentId = agentId;
    }

    public String getAgtFirstName() {
        return AgtFirstName;
    }

    public void setAgtFirstName(String agtFirstName) {
        AgtFirstName = agtFirstName;
    }

    public String getAgtMiddleName() {
        return AgtMiddleName;
    }

    public void setAgtMiddleName(String agtMiddleName) {
        AgtMiddleName = agtMiddleName;
    }

    public String getAgtLastName() {
        return AgtLastName;
    }

    public void setAgtLastName(String agtLastName) {
        AgtLastName = agtLastName;
    }

    public String getBusPhone() {
        return BusPhone;
    }

    public void setBusPhone(String busPhone) {
        BusPhone = busPhone;
    }

    public String getAgtEmail() {
        return AgtEmail;
    }

    public void setAgtEmail(String agtEmail) {
        AgtEmail = agtEmail;
    }

    public String getAgtPosition() {
        return AgtPosition;
    }

    public void setAgtPosition(String agtPosition) {
        AgtPosition = agtPosition;
    }

    public int getAgencyId() {
        return AgencyId;
    }

    public void setAgencyId(int agencyId) {
        AgencyId = agencyId;
    }

    @Override
    public String toString() {
        return AgtFirstName + ' ' + AgtMiddleName;
    }
}
