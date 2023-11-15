package concurrent;

import java.util.HashMap;
import java.util.HashSet;

public class Problem4 {

    public Problem4() {
        this.userIdAndModelIds = new HashMap<>();
    }

    HashMap<Integer, HashSet<Integer>> userIdAndModelIds = new HashMap<>();

    /**
     * 开启一个功能模块
     * @param userId
     * @param ModelId
     */
    public void open(Integer userId, Integer ModelId) {
        HashSet<Integer> modelIds = userIdAndModelIds.get(userId);
        if (modelIds == null) {
            modelIds = new HashSet<>();
            modelIds.add(ModelId);
            userIdAndModelIds.put(userId, modelIds);
        } else {
            modelIds.add(ModelId);
        }
    }

    /**
     * 判断是否开启了某个功能模块
     * @param userId
     * @param modelId
     * @return
     */
    public boolean openModel(Integer userId, Integer modelId) {
        HashSet<Integer> modelIds = userIdAndModelIds.get(userId);
        if (modelIds == null || !modelIds.contains(modelId)) {
            return false;
        }
        return true;
    }

    /**
     * 返回用户已经开启的所有功能模块
     * @param userId
     * @return
     */
    public HashSet<Integer> userAllModels(Integer userId) {
        HashSet<Integer> modelIds = userIdAndModelIds.get(userId);
        return modelIds == null ? new HashSet<>() : modelIds;
    }

}
