package com.ashunevich.android_retrofit2_test;

import java.util.List;

public class PresenterImpl implements Contractor.Presenter, Contractor.Interactor.onGetPostsListener,
        Contractor.Interactor.onNewPostListener, Contractor.Interactor.onDeletePostListener,
        Contractor.Interactor.onPatchPostListener, Contractor.Interactor.onPutPostListener {

    private final Contractor.View view;
    private final Contractor.Interactor interactor;

    public PresenterImpl (Contractor.View view,Contractor.Interactor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getPosts() {
            interactor.getPosts (this);
    }

    @Override
    public void newPost(ItemJSON itemJSON) {
        interactor.newPosts (this,itemJSON);
    }

    @Override
    public void putPost(String id, ItemJSON itemJSON) {
        interactor.putPost (this,id,itemJSON);
    }

    @Override
    public void deletePost(String id) {
        interactor.deletePost (this,id);
    }

    @Override
    public void patchPost(String id, ItemJSON itemJSON) {
        interactor.patchPost (this,id,itemJSON);
    }


    @Override
    public void onFinished(List<ItemJSON> listCall) {
        view.parseDataToRecyclerView (listCall);
    }

    @Override
    public void onFinished(ItemJSON item) {
        view.createJsonObject (item);
    }

    @Override
    public void onFinished(String id) {
        view.setResponseString (id);
    }

    @Override
    public void onFinished(String id, ItemJSON itemJSON) {

    }

    @Override
    public void onFailure(Throwable t) {
        view.onResponseFailure (t);
    }
}
