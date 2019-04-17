package com.example.luyiling.recyclerpractice.group;

/**
 * Created by luyiling on 2019/4/17
 * <p>
 * TODO:
 *
 * <IMPORTANT></IMPORTANT>
 */
public class GroupData {
    String id;
    String fig_url;
    String dest_url;

    public GroupData(String id, String fig_url, String dest_url) {
        this.id = id;
        this.fig_url = fig_url;
        this.dest_url = dest_url;
    }

    public String getId() {
        return id;
    }

    public String getFig_url() {
        return fig_url;
    }

    public String getDest_url() {
        return dest_url;
    }
}
