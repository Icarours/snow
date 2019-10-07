package com.syl.snow.fragment.content4.mvp.m;

/**
 * Created by Bright on 2019/10/7.
 *
 * @Describe
 * @Called
 */
public class MvpDataModelManager {
    public static MvpBaseModel sMvpBaseModel;

    public static MvpBaseModel newInstance(String modelName) {
        try {
            sMvpBaseModel = (MvpBaseModel) Class.forName(modelName).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return sMvpBaseModel;
    }
}
