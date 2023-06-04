package akira.d.chatbot.api.domain.zsxq.model.aggregates;

import akira.d.chatbot.api.domain.zsxq.model.res.Resp_data;
public class UnAnsweredQuestionsAggregates {
    private boolean succeeded;
    private Resp_data resp_data;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public Resp_data getResp_data() {
        return resp_data;
    }

    public void setResp_data(Resp_data resp_data) {
        this.resp_data = resp_data;
    }
}
