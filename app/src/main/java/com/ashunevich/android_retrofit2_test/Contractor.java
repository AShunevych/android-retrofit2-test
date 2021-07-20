package com.ashunevich.android_retrofit2_test;

import java.util.List;

public interface Contractor {

    interface Presenter {
        void getPosts();
        void newPost(ItemJSON itemJSON);
        void getPostById(int id);
        void deletePost(int id);
        /*
        void patchPost(String id, ItemJSON itemJSON);
        void putPost(String id, ItemJSON itemJSON);
         */
    }

    interface View {
        void parseDataToRecyclerView(List<ItemJSON> listCall);

        void createJsonObject(ItemJSON itemJSON);

         void setResponseString(int s);

        void onResponseFailure(Throwable throwable);
    }

    interface Interactor {

        interface onGetPostsListener {
            void onSuccessGetPostCall(List<ItemJSON> listCall);
            void onFailure(Throwable t);
        }

        interface onNewPostListener {
            void onSuccessNewPostCall(ItemJSON item);
            void onFailure(Throwable t);
        }

        interface onDeletePostListener{
            void onSucessDeletePostCall(int id);
            void onFailure(Throwable t);
        }

        interface  onGetPostByIDListener{
            void onSuccessGetPostByID(ItemJSON json);
            void onFailure(Throwable t);
        }

            /*
        interface onPatchPostListener{
            void onFinished(String id, ItemJSON itemJSON);
            void onFailure(Throwable t);
        }

        interface onPutPostListener{
            void onFinished(String id, ItemJSON itemJSON);
            void onFailure(Throwable t);
        }
             */

        void getPosts(onGetPostsListener onGetPostsListener);

        void getPostByID(onGetPostByIDListener onGetPostByIDListener, int id);

        void newPosts(onNewPostListener onNewPostListener,ItemJSON item);

        void deletePost(onDeletePostListener onDeletePostLiener, int id);
/*
        void patchPost(onPatchPostListener onPatchPostListener,String id, ItemJSON itemJSON);

        void putPost(onPutPostListener onPutPostListener,String id, ItemJSON itemJSON);
 */
    }
}
