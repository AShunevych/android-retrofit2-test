package com.ashunevich.android_retrofit2_test;

import java.util.List;

public class PresenterImpl implements Contractor.Presenter, Contractor.Interactor.onGetPostsListener,
        Contractor.Interactor.onNewPostListener, Contractor.Interactor.onDeletePostListener,
        Contractor.Interactor.onGetPostByIDListener {

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
    public void getPostById(int id) {
        interactor.getPostByID (this,id);
    }

    @Override
    public void deletePost(int id) {
        interactor.deletePost (this,id);
    }

    @Override
    public void onSuccessGetPostCall(List<ItemJSON> listCall) {
        view.parseDataToRecyclerView (listCall);
    }

    @Override
    public void onSuccessNewPostCall(ItemJSON item) {
        view.createJsonObject (item);
    }

    @Override
    public void onSucessDeletePostCall(int id) {
        view.setResponseString (id);
    }

    @Override
    public void onSuccessGetPostByID(ItemJSON json) {
        view.createJsonObject (json);
    }

    @Override
    public void onFailure(Throwable t) {
        view.onResponseFailure (t);
    }
}
