package com.ashunevich.android_retrofit2_test;

import java.util.List;

public interface Contractor {

    interface Presenter {
        void getPosts();
        void newPost(ItemJSON itemJSON);
        void putPost(String id, ItemJSON itemJSON);
        void deletePost(String id);
        void patchPost(String id, ItemJSON itemJSON);
    }

    interface View {

        void parseDataToRecyclerView(List<ItemJSON> listCall);

        void createJsonObject(ItemJSON itemJSON);

         void setResponseString(String s);

        void onResponseFailure(Throwable throwable);
    }

    interface Interactor {

        interface onGetPostsListener {
            void onFinished(List<ItemJSON> listCall);
            void onFailure(Throwable t);
        }

        interface onNewPostListener {
            void onFinished(ItemJSON item);
            void onFailure(Throwable t);
        }

        interface onDeletePostListener{
            void onFinished(String id);
            void onFailure(Throwable t);
        }

        interface onPatchPostListener{
            void onFinished(String id, ItemJSON itemJSON);
            void onFailure(Throwable t);
        }

        interface onPutPostListener{
            void onFinished(String id, ItemJSON itemJSON);
            void onFailure(Throwable t);
        }

        void getPosts(onGetPostsListener onGetPostsListener);

        void newPosts(onNewPostListener onNewPostListener,ItemJSON item);

        void deletePost(onDeletePostListener onDeletePostLiener,String id);

        void patchPost(onPatchPostListener onPatchPostListener,String id, ItemJSON itemJSON);

        void putPost(onPutPostListener onPutPostListener,String id, ItemJSON itemJSON);
    }
}
