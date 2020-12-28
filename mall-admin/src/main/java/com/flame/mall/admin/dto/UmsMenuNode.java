package com.flame.mall.admin.dto;

import com.flame.mall.mbg.model.UmsMenu;
import lombok.Data;

import java.util.List;


/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/28 16:39
 */
@Data
public class UmsMenuNode extends UmsMenu {
    private List<UmsMenuNode> children;
}
