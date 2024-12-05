///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Backend.Feed;
//
//import Backend.Account.Account;
//import org.json.JSONArray;
//
///**
// *
// * @author LEGION
// */
//public class ContentDatabase {
//    
//    private void saveContent() {                            //awaiting modifications in the "content" class logic
//        JSONArray usersArray = new JSONArray();
//        for (Account acc : accounts) {
//            JSONObject obj = new JSONObject();
//            obj.put("userid", acc.getUserId());
//            obj.put("postscount", acc.getProfile().getPostsCount());
//            obj.put("storiescount", acc.getProfile().getStoriesCount());
//            for (Content content : acc.getProfile().getContent()) {
//                if (content instanceof Posts) {
//                    obj.put("type", "post");
//                } else {
//                    obj.put("type", "story");
//                }
//                obj.put("contentid", content.getContentId());
//                obj.put("imagepath", content.getContent());
//                obj.put("timestamp", content.getTime());
//
//            }
//            usersArray.put(obj);
//        }
//        try {
//            FileWriter file = new FileWriter("content.json");
//            file.write("");
//            file.write(usersArray.toString(3));
//            file.flush();
//            file.close();
//        } catch (IOException e) {
//            System.out.println("Error in saving content.json");
//        }
//    }
//    
//}
