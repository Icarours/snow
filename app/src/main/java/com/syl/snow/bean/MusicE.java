package com.syl.snow.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Bright on 2019/2/28.
 *
 * @Describe  请求音乐文件
 * http://cloud.lanlyc.cn/lanlyc_gongdi/ttmusic/musicList?paramJson={"mobile":"15989469069"}
 * @Called
 */
public class MusicE implements Serializable,Parcelable {

    /**
     * trackLength : 03:38
     * channels : Joint Stereo
     * bitRate : 128
     * startByte : 113799
     * format : MPEG-1 Layer 3
     * music_name : 陈鸿宇-理想三旬.mp3
     * sampleRate : 44100
     * url : http://59.175.153.46:8068/EmpPicUpload/musicList/陈鸿宇-理想三旬.mp3
     */

    private String trackLength;
    private String channels;
    private String bitRate;
    private int startByte;
    private String format;
    private String music_name;
    private String sampleRate;
    private String url;
    private String tips;//提示可以播放音乐
    private boolean check;

    public MusicE() {
    }

    protected MusicE(Parcel in) {
        trackLength = in.readString();
        channels = in.readString();
        bitRate = in.readString();
        startByte = in.readInt();
        format = in.readString();
        music_name = in.readString();
        sampleRate = in.readString();
        url = in.readString();
        tips = in.readString();
        check = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(trackLength);
        dest.writeString(channels);
        dest.writeString(bitRate);
        dest.writeInt(startByte);
        dest.writeString(format);
        dest.writeString(music_name);
        dest.writeString(sampleRate);
        dest.writeString(url);
        dest.writeString(tips);
        dest.writeByte((byte) (check ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MusicE> CREATOR = new Creator<MusicE>() {
        @Override
        public MusicE createFromParcel(Parcel in) {
            return new MusicE(in);
        }

        @Override
        public MusicE[] newArray(int size) {
            return new MusicE[size];
        }
    };

    @Override
    public String toString() {
        return "MusicE{" +
                "trackLength='" + trackLength + '\'' +
                ", channels='" + channels + '\'' +
                ", bitRate='" + bitRate + '\'' +
                ", startByte=" + startByte +
                ", format='" + format + '\'' +
                ", music_name='" + music_name + '\'' +
                ", sampleRate='" + sampleRate + '\'' +
                ", url='" + url + '\'' +
                ", tips='" + tips + '\'' +
                ", check=" + check +
                '}';
    }

    public String getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(String trackLength) {
        this.trackLength = trackLength;
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    public String getBitRate() {
        return bitRate;
    }

    public void setBitRate(String bitRate) {
        this.bitRate = bitRate;
    }

    public int getStartByte() {
        return startByte;
    }

    public void setStartByte(int startByte) {
        this.startByte = startByte;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMusic_name() {
        return music_name;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    public String getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(String sampleRate) {
        this.sampleRate = sampleRate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
