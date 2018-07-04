package com.maxlong.study.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/5/25 16:50
 *          类说明
 */
@Slf4j
public enum CreateModeMy {

    /**
     * 持久节点：节点创建后，会一直存在，不会因客户端会话失效而删除；
     */
    PERSISTENT (0, false, false),
    /**
     * 持久顺序节点：基本特性与持久节点一致，创建节点的过程中，zookeeper会在其名字后自动追加一个单调增长的数字后缀，作为新的节点名；
     */
    PERSISTENT_SEQUENTIAL (2, false, true),

    /**
     *  临时节点：客户端会话失效或连接关闭后，该节点会被自动删除，且不能再临时节点下面创建子节点，否则报如下错：org.apache.zookeeper.KeeperException$NoChildrenForEphemeralsException；
     */
    EPHEMERAL (1, true, false),

    /**
     * 临时顺序节点：基本特性与临时节点一致，创建节点的过程中，zookeeper会在其名字后自动追加一个单调增长的数字后缀，作为新的节点名；
     */
    EPHEMERAL_SEQUENTIAL (3, true, true);
    private boolean ephemeral;
    private boolean sequential;
    private int flag;
    CreateModeMy(int flag, boolean ephemeral, boolean sequential) {
        this.flag = flag;
        this.ephemeral = ephemeral;
        this.sequential = sequential;
    }
    public boolean isEphemeral() {
        return ephemeral;
    }
    public boolean isSequential() {
        return sequential;
    }
    public int toFlag() {
        return flag;
    }

    static public CreateModeMy fromFlag(int flag) throws KeeperException {
        switch(flag) {
            case 0: return CreateModeMy.PERSISTENT;
            case 1: return CreateModeMy.EPHEMERAL;
            case 2: return CreateModeMy.PERSISTENT_SEQUENTIAL;
            case 3: return CreateModeMy.EPHEMERAL_SEQUENTIAL ;
            default:
                log.error("Received an invalid flag value to convert to a CreateModeMy");
                throw new KeeperException.BadArgumentsException();
        }
    }
}