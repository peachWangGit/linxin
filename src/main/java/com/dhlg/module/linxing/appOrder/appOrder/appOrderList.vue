<template>
    <div class="app-container">
        <div class="container">
            <!-- 区域一 查询区域 -->
            <div class="container_btn" >
            <span>
            <el-button type="warning" size="small"   @click="handleAdd">新增</el-button>
            <el-button type="danger" size="small" :disabled="dicDisabled"  @click="handleDeleteBatch">删除</el-button>
            <el-button type="primary" plain size="small"   @click="search">刷新</el-button>
			<el-button type="info" plain size="small" @click="reset">重置搜索</el-button>
            </span>
            </div>
            <!-- 区域二---表格+分页 -->
            <el-table :data="pageData.list" style="width: 100%" height="100%" :row-style="{height:'50px'}" border class="table" ref="multipleTable" align="center" @selection-change="handleSelectionChange" >
                <el-table-column type="selection"  fixed width="45"  align="center" show-overflow-tooltip></el-table-column>
                <el-table-column type="index" width="55" label="序号" align="center" v-if="xuhao" show-overflow-tooltip></el-table-column>
                        <el-table-column prop="address" min-width="140" label="地址" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.address">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.address"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('address')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('address')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="category" min-width="140" label="1积分订单2普通订单" align="center" show-overflow-tooltip>
                                    <template slot="header" slot-scope="scope">
                                        <span class="col-filter">
                                        {{scope.column.label}}
                                        <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.category">
                                            <div style="max-height: 300px; overflow-y: auto;">
                                            <ul style="list-style: none;">
                                                <li
                                                        v-for="item in checkBoxList.categoryList"
                                                        :key="item.value"
                                                        :label="item.label"
                                                >
                                                <el-checkbox :label="item.label" v-model="item.checked"></el-checkbox>
                                                </li>
                                            </ul>
                                            </div>
                                            <hr style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"/>
                                            <div style="display: flex; justify-content: space-evenly;">
                                                <el-button type="text" @click="handleCheckboxListFilterOk('category')">确定</el-button>
                                                <el-button type="text" @click="handleCheckboxListFilterCancel('category')">重置</el-button>
                                            </div>
                                            <i slot="reference" class="icon-filter"  @click.stop></i>
                                        </el-popover>
                                        </span>
                                    </template>

                                    <template slot-scope="scope">
                                        <div >
                                            <div v-for="item in checkBoxList.categoryList" :key="item.value">
                                                <el-tag
                                                        type="success"
                                                        v-if="scope.row.category==item.value"
                                                >{{item.label}}</el-tag>
                                            </div>
                                        </div>
                                    </template>


                        </el-table-column>
                        <el-table-column prop="city" min-width="140" label="市" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.city">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.city"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('city')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('city')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="contact" min-width="140" label="收货人" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.contact">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.contact"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('contact')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('contact')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="county" min-width="140" label="区" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.county">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.county"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('county')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('county')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="courierCompany" min-width="140" label="快递公司" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.courierCompany">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.courierCompany"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('courierCompany')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('courierCompany')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="courierNumber" min-width="140" label="快递单号" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.courierNumber">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.courierNumber"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('courierNumber')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('courierNumber')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="creatTime" min-width="140" label="创建时间" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="400" trigger="click"  v-model="queryVisible.creatTime">
                                        <el-date-picker
                                                v-model="condition.creatTime"
                                                type="daterange"
                                                align="right"
                                                unlink-panels
                                                range-separator="至"
                                                start-placeholder="开始日期"
                                                end-placeholder="结束日期"
                                                :picker-options="pickerOptions"
                                                value-format="yyyy-MM-dd HH:mm:ss"
                                        ></el-date-picker>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button
                                                type="text"
                                                @click="handleDateRangeListFilterOk('creatTime')"
                                        >确定</el-button>
                                        <el-button
                                                type="text"
                                                @click="handleDateRangeListFilterCancel('creatTime')"
                                        >重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter" @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="getToken" min-width="140" label="自取件取件token" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.getToken">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.getToken"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('getToken')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('getToken')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="kind" min-width="140" label="1自取2快递" align="center" show-overflow-tooltip>
                                    <template slot="header" slot-scope="scope">
                                        <span class="col-filter">
                                        {{scope.column.label}}
                                        <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.kind">
                                            <div style="max-height: 300px; overflow-y: auto;">
                                            <ul style="list-style: none;">
                                                <li
                                                        v-for="item in checkBoxList.kindList"
                                                        :key="item.value"
                                                        :label="item.label"
                                                >
                                                <el-checkbox :label="item.label" v-model="item.checked"></el-checkbox>
                                                </li>
                                            </ul>
                                            </div>
                                            <hr style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"/>
                                            <div style="display: flex; justify-content: space-evenly;">
                                                <el-button type="text" @click="handleCheckboxListFilterOk('kind')">确定</el-button>
                                                <el-button type="text" @click="handleCheckboxListFilterCancel('kind')">重置</el-button>
                                            </div>
                                            <i slot="reference" class="icon-filter"  @click.stop></i>
                                        </el-popover>
                                        </span>
                                    </template>

                                    <template slot-scope="scope">
                                        <div >
                                            <div v-for="item in checkBoxList.kindList" :key="item.value">
                                                <el-tag
                                                        type="success"
                                                        v-if="scope.row.kind==item.value"
                                                >{{item.label}}</el-tag>
                                            </div>
                                        </div>
                                    </template>


                        </el-table-column>
                        <el-table-column prop="orderSn" min-width="140" label="订单编号" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.orderSn">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.orderSn"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('orderSn')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('orderSn')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="orderStatus" min-width="140" label="订单状态" align="center" show-overflow-tooltip>
                                    <template slot="header" slot-scope="scope">
                                        <span class="col-filter">
                                        {{scope.column.label}}
                                        <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.orderStatus">
                                            <div style="max-height: 300px; overflow-y: auto;">
                                            <ul style="list-style: none;">
                                                <li
                                                        v-for="item in checkBoxList.orderStatusList"
                                                        :key="item.value"
                                                        :label="item.label"
                                                >
                                                <el-checkbox :label="item.label" v-model="item.checked"></el-checkbox>
                                                </li>
                                            </ul>
                                            </div>
                                            <hr style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"/>
                                            <div style="display: flex; justify-content: space-evenly;">
                                                <el-button type="text" @click="handleCheckboxListFilterOk('orderStatus')">确定</el-button>
                                                <el-button type="text" @click="handleCheckboxListFilterCancel('orderStatus')">重置</el-button>
                                            </div>
                                            <i slot="reference" class="icon-filter"  @click.stop></i>
                                        </el-popover>
                                        </span>
                                    </template>

                                    <template slot-scope="scope">
                                        <div >
                                            <div v-for="item in checkBoxList.orderStatusList" :key="item.value">
                                                <el-tag
                                                        type="success"
                                                        v-if="scope.row.orderStatus==item.value"
                                                >{{item.label}}</el-tag>
                                            </div>
                                        </div>
                                    </template>


                        </el-table-column>
                        <el-table-column prop="payId" min-width="140" label="付款流水id" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.payId">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.payId"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('payId')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('payId')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="payIntegral" min-width="140" label="支付积分" align="center" show-overflow-tooltip>
                                    <template slot="header" slot-scope="scope">
                                        <span class="col-filter">
                                        {{scope.column.label}}
                                        <el-popover placement="bottom-end" width="300" trigger="click" v-model="queryVisible.payIntegral">
                                            <div>
                                                <el-input size="small"  v-model="condition.payIntegral[0]" placeholder="请输入数字" oninput ="value=value.replace(/[^\d]/g,'')" style="width:120px"></el-input> ——
                                                <el-input size="small"  v-model="condition.payIntegral[1]" placeholder="请输入数字" oninput ="value=value.replace(/[^\d]/g,'')" style="width:120px"></el-input>
                                            </div>
                                            <hr style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"/>
                                            <div style="display: flex; justify-content: space-evenly;">
                                                <el-button type="text" @click="handleNumFilterOk('payIntegral')">确定</el-button>
                                                <el-button type="text" @click="handleNumFilterCancel('payIntegral')">重置</el-button>
                                            </div>
                                            <i slot="reference" class="icon-filter"  @click.stop></i>
                                        </el-popover>
                                        </span>
                                    </template>

                        </el-table-column>
                        <el-table-column prop="payPrice" min-width="140" label="支付金额" align="center" show-overflow-tooltip>
                                    <template slot="header" slot-scope="scope">
                                        <span class="col-filter">
                                        {{scope.column.label}}
                                        <el-popover placement="bottom-end" width="300" trigger="click" v-model="queryVisible.payPrice">
                                            <div>
                                                <el-input size="small"  v-model="condition.payPrice[0]" placeholder="请输入数字" oninput ="value=value.replace(/[^\d]/g,'')" style="width:120px"></el-input> ——
                                                <el-input size="small"  v-model="condition.payPrice[1]" placeholder="请输入数字" oninput ="value=value.replace(/[^\d]/g,'')" style="width:120px"></el-input>
                                            </div>
                                            <hr style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"/>
                                            <div style="display: flex; justify-content: space-evenly;">
                                                <el-button type="text" @click="handleNumFilterOk('payPrice')">确定</el-button>
                                                <el-button type="text" @click="handleNumFilterCancel('payPrice')">重置</el-button>
                                            </div>
                                            <i slot="reference" class="icon-filter"  @click.stop></i>
                                        </el-popover>
                                        </span>
                                    </template>

                        </el-table-column>
                        <el-table-column prop="payTime" min-width="140" label="支付时间" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="400" trigger="click"  v-model="queryVisible.payTime">
                                        <el-date-picker
                                                v-model="condition.payTime"
                                                type="daterange"
                                                align="right"
                                                unlink-panels
                                                range-separator="至"
                                                start-placeholder="开始日期"
                                                end-placeholder="结束日期"
                                                :picker-options="pickerOptions"
                                                value-format="yyyy-MM-dd HH:mm:ss"
                                        ></el-date-picker>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button
                                                type="text"
                                                @click="handleDateRangeListFilterOk('payTime')"
                                        >确定</el-button>
                                        <el-button
                                                type="text"
                                                @click="handleDateRangeListFilterCancel('payTime')"
                                        >重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter" @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="phone" min-width="140" label="联系电话" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.phone">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.phone"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('phone')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('phone')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="postage" min-width="140" label="邮费" align="center" show-overflow-tooltip>
                                    <template slot="header" slot-scope="scope">
                                        <span class="col-filter">
                                        {{scope.column.label}}
                                        <el-popover placement="bottom-end" width="300" trigger="click" v-model="queryVisible.postage">
                                            <div>
                                                <el-input size="small"  v-model="condition.postage[0]" placeholder="请输入数字" oninput ="value=value.replace(/[^\d]/g,'')" style="width:120px"></el-input> ——
                                                <el-input size="small"  v-model="condition.postage[1]" placeholder="请输入数字" oninput ="value=value.replace(/[^\d]/g,'')" style="width:120px"></el-input>
                                            </div>
                                            <hr style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"/>
                                            <div style="display: flex; justify-content: space-evenly;">
                                                <el-button type="text" @click="handleNumFilterOk('postage')">确定</el-button>
                                                <el-button type="text" @click="handleNumFilterCancel('postage')">重置</el-button>
                                            </div>
                                            <i slot="reference" class="icon-filter"  @click.stop></i>
                                        </el-popover>
                                        </span>
                                    </template>

                        </el-table-column>
                        <el-table-column prop="province" min-width="140" label="省" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.province">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.province"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('province')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('province')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="receiptTime" min-width="140" label="收货时间" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="400" trigger="click"  v-model="queryVisible.receiptTime">
                                        <el-date-picker
                                                v-model="condition.receiptTime"
                                                type="daterange"
                                                align="right"
                                                unlink-panels
                                                range-separator="至"
                                                start-placeholder="开始日期"
                                                end-placeholder="结束日期"
                                                :picker-options="pickerOptions"
                                                value-format="yyyy-MM-dd HH:mm:ss"
                                        ></el-date-picker>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button
                                                type="text"
                                                @click="handleDateRangeListFilterOk('receiptTime')"
                                        >确定</el-button>
                                        <el-button
                                                type="text"
                                                @click="handleDateRangeListFilterCancel('receiptTime')"
                                        >重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter" @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="storeId" min-width="140" label="店铺id" align="center" show-overflow-tooltip>
                                    <template slot="header" slot-scope="scope">
                                        <span class="col-filter">
                                        {{scope.column.label}}
                                        <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.storeId">
                                            <div style="max-height: 300px; overflow-y: auto;">
                                            <ul style="list-style: none;">
                                                <li
                                                        v-for="item in checkBoxList.storeIdList"
                                                        :key="item.value"
                                                        :label="item.label"
                                                >
                                                <el-checkbox :label="item.label" v-model="item.checked"></el-checkbox>
                                                </li>
                                            </ul>
                                            </div>
                                            <hr style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"/>
                                            <div style="display: flex; justify-content: space-evenly;">
                                                <el-button type="text" @click="handleCheckboxListFilterOk('storeId')">确定</el-button>
                                                <el-button type="text" @click="handleCheckboxListFilterCancel('storeId')">重置</el-button>
                                            </div>
                                            <i slot="reference" class="icon-filter"  @click.stop></i>
                                        </el-popover>
                                        </span>
                                    </template>

                                    <template slot-scope="scope">
                                        <div >
                                            <div v-for="item in checkBoxList.storeIdList" :key="item.value">
                                                <el-tag
                                                        type="success"
                                                        v-if="scope.row.storeId==item.value"
                                                >{{item.label}}</el-tag>
                                            </div>
                                        </div>
                                    </template>


                        </el-table-column>
                        <el-table-column prop="tokenTime" min-width="140" label="token时间" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="400" trigger="click"  v-model="queryVisible.tokenTime">
                                        <el-date-picker
                                                v-model="condition.tokenTime"
                                                type="daterange"
                                                align="right"
                                                unlink-panels
                                                range-separator="至"
                                                start-placeholder="开始日期"
                                                end-placeholder="结束日期"
                                                :picker-options="pickerOptions"
                                                value-format="yyyy-MM-dd HH:mm:ss"
                                        ></el-date-picker>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button
                                                type="text"
                                                @click="handleDateRangeListFilterOk('tokenTime')"
                                        >确定</el-button>
                                        <el-button
                                                type="text"
                                                @click="handleDateRangeListFilterCancel('tokenTime')"
                                        >重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter" @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="userId" min-width="140" label="用户id" align="center" show-overflow-tooltip>
                                    <template slot="header" slot-scope="scope">
                                        <span class="col-filter">
                                        {{scope.column.label}}
                                        <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.userId">
                                            <div style="max-height: 300px; overflow-y: auto;">
                                            <ul style="list-style: none;">
                                                <li
                                                        v-for="item in checkBoxList.userIdList"
                                                        :key="item.value"
                                                        :label="item.label"
                                                >
                                                <el-checkbox :label="item.label" v-model="item.checked"></el-checkbox>
                                                </li>
                                            </ul>
                                            </div>
                                            <hr style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"/>
                                            <div style="display: flex; justify-content: space-evenly;">
                                                <el-button type="text" @click="handleCheckboxListFilterOk('userId')">确定</el-button>
                                                <el-button type="text" @click="handleCheckboxListFilterCancel('userId')">重置</el-button>
                                            </div>
                                            <i slot="reference" class="icon-filter"  @click.stop></i>
                                        </el-popover>
                                        </span>
                                    </template>

                                    <template slot-scope="scope">
                                        <div >
                                            <div v-for="item in checkBoxList.userIdList" :key="item.value">
                                                <el-tag
                                                        type="success"
                                                        v-if="scope.row.userId==item.value"
                                                >{{item.label}}</el-tag>
                                            </div>
                                        </div>
                                    </template>


                        </el-table-column>

                <el-table-column label="操作" fixed="right" min-width="160" align="center" show-overflow-tooltip>
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-view" class="yellow"  @click="handlePre(scope.row)" >预览</el-button>
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.row)" >编辑</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red"  @click="handleDelete(scope.row.id)" >删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <pagination :page-list="pageData" @pagesearch="handlePage"></pagination>
            </div>
        </div>
        <!-- 区域三---弹出框，覆盖全部 -->
        <div class="showDialog">
            <el-dialog  :visible.sync="editVisible"  :show-close="false" :modal="false" fullscreen>
                <div class="dialogFix">
                    <template v-if="!preVisiable">
                        <el-button type="primary" size="small"  @click="handleSave('form')">保 存</el-button>
                    </template>
                    <el-button size="small" @click="cancelSave">返 回</el-button>
                </div>
                <el-row class="dialogForm">
                    <el-col :span="24">
                        <div style="margin-top: 42px;">

                            <el-form  ref="form" :model="form" :rules="rules" label-width="150px" size="small" style="margin-top:10px">
                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="地址" prop="address" style="width:35rem;height:32px">
                                                    <el-input v-model="form.address" :disabled="preVisiable"></el-input>
                                                </el-form-item>
                                            </el-col>





                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="1积分订单2普通订单" prop="category" style="max-width:35rem;height:32px">
                                                    <el-select v-model="form.category"  placeholder="请选择"  style="width:100%;" :disabled="preVisiable">
                                                        <el-option
                                                                v-for="item in checkBoxList.categoryList"
                                                                :key="item.value"
                                                                :label="item.label"
                                                                :value="item.value"
                                                        ></el-option>
                                                    </el-select>
                                                </el-form-item>
                                            </el-col>




                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="市" prop="city" style="width:35rem;height:32px">
                                                    <el-input v-model="form.city" :disabled="preVisiable"></el-input>
                                                </el-form-item>
                                            </el-col>




                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="收货人" prop="contact" style="width:35rem;height:32px">
                                                    <el-input v-model="form.contact" :disabled="preVisiable"></el-input>
                                                </el-form-item>
                                            </el-col>




                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="区" prop="county" style="width:35rem;height:32px">
                                                    <el-input v-model="form.county" :disabled="preVisiable"></el-input>
                                                </el-form-item>
                                            </el-col>




                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="快递公司" prop="courierCompany" style="width:35rem;height:32px">
                                                    <el-input v-model="form.courierCompany" :disabled="preVisiable"></el-input>
                                                </el-form-item>
                                            </el-col>




                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="快递单号" prop="courierNumber" style="width:35rem;height:32px">
                                                    <el-input v-model="form.courierNumber" :disabled="preVisiable"></el-input>
                                                </el-form-item>
                                            </el-col>




                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="创建时间" prop="creatTime" style="max-width:35rem;height:32px">
                                                    <el-date-picker
                                                            :disabled="preVisiable"
                                                            v-model="form.creatTime"
                                                            type="datetime"
                                                            placeholder="选择日期时间"
                                                            default-time="12:00:00"
                                                            style="width:100%">
                                                    </el-date-picker>
                                                </el-form-item>
                                            </el-col>



                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="自取件取件token" prop="getToken" style="width:35rem;height:32px">
                                                    <el-input v-model="form.getToken" :disabled="preVisiable"></el-input>
                                                </el-form-item>
                                            </el-col>





                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="1自取2快递" prop="kind" style="max-width:35rem;height:32px">
                                                    <el-select v-model="form.kind"  placeholder="请选择"  style="width:100%;" :disabled="preVisiable">
                                                        <el-option
                                                                v-for="item in checkBoxList.kindList"
                                                                :key="item.value"
                                                                :label="item.label"
                                                                :value="item.value"
                                                        ></el-option>
                                                    </el-select>
                                                </el-form-item>
                                            </el-col>




                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="订单编号" prop="orderSn" style="width:35rem;height:32px">
                                                    <el-input v-model="form.orderSn" :disabled="preVisiable"></el-input>
                                                </el-form-item>
                                            </el-col>





                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="订单状态" prop="orderStatus" style="max-width:35rem;height:32px">
                                                    <el-select v-model="form.orderStatus"  placeholder="请选择"  style="width:100%;" :disabled="preVisiable">
                                                        <el-option
                                                                v-for="item in checkBoxList.orderStatusList"
                                                                :key="item.value"
                                                                :label="item.label"
                                                                :value="item.value"
                                                        ></el-option>
                                                    </el-select>
                                                </el-form-item>
                                            </el-col>




                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="付款流水id" prop="payId" style="width:35rem;height:32px">
                                                    <el-input v-model="form.payId" :disabled="preVisiable"></el-input>
                                                </el-form-item>
                                            </el-col>





                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="支付积分" prop="payIntegral" style="max-width:35rem;height:32px">
                                                    <el-input v-model="form.payIntegral" oninput ="value=value.replace(/[^\d]/g,'')" :disabled="preVisiable"></el-input>
                                                </el-form-item>
                                            </el-col>





                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="支付金额" prop="payPrice" style="max-width:35rem;height:32px">
                                                    <el-input v-model="form.payPrice" oninput ="value=value.replace(/[^\d]/g,'')" :disabled="preVisiable"></el-input>
                                                </el-form-item>
                                            </el-col>




                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="支付时间" prop="payTime" style="max-width:35rem;height:32px">
                                                    <el-date-picker
                                                            :disabled="preVisiable"
                                                            v-model="form.payTime"
                                                            type="datetime"
                                                            placeholder="选择日期时间"
                                                            default-time="12:00:00"
                                                            style="width:100%">
                                                    </el-date-picker>
                                                </el-form-item>
                                            </el-col>



                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="联系电话" prop="phone" style="width:35rem;height:32px">
                                                    <el-input v-model="form.phone" :disabled="preVisiable"></el-input>
                                                </el-form-item>
                                            </el-col>





                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="邮费" prop="postage" style="max-width:35rem;height:32px">
                                                    <el-input v-model="form.postage" oninput ="value=value.replace(/[^\d]/g,'')" :disabled="preVisiable"></el-input>
                                                </el-form-item>
                                            </el-col>




                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="省" prop="province" style="width:35rem;height:32px">
                                                    <el-input v-model="form.province" :disabled="preVisiable"></el-input>
                                                </el-form-item>
                                            </el-col>




                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="收货时间" prop="receiptTime" style="max-width:35rem;height:32px">
                                                    <el-date-picker
                                                            :disabled="preVisiable"
                                                            v-model="form.receiptTime"
                                                            type="datetime"
                                                            placeholder="选择日期时间"
                                                            default-time="12:00:00"
                                                            style="width:100%">
                                                    </el-date-picker>
                                                </el-form-item>
                                            </el-col>




                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="店铺id" prop="storeId" style="max-width:35rem;height:32px">
                                                    <el-select v-model="form.storeId"  placeholder="请选择"  style="width:100%;" :disabled="preVisiable">
                                                        <el-option
                                                                v-for="item in checkBoxList.storeIdList"
                                                                :key="item.value"
                                                                :label="item.label"
                                                                :value="item.value"
                                                        ></el-option>
                                                    </el-select>
                                                </el-form-item>
                                            </el-col>




                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="token时间" prop="tokenTime" style="max-width:35rem;height:32px">
                                                    <el-date-picker
                                                            :disabled="preVisiable"
                                                            v-model="form.tokenTime"
                                                            type="datetime"
                                                            placeholder="选择日期时间"
                                                            default-time="12:00:00"
                                                            style="width:100%">
                                                    </el-date-picker>
                                                </el-form-item>
                                            </el-col>




                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="用户id" prop="userId" style="max-width:35rem;height:32px">
                                                    <el-select v-model="form.userId"  placeholder="请选择"  style="width:100%;" :disabled="preVisiable">
                                                        <el-option
                                                                v-for="item in checkBoxList.userIdList"
                                                                :key="item.value"
                                                                :label="item.label"
                                                                :value="item.value"
                                                        ></el-option>
                                                    </el-select>
                                                </el-form-item>
                                            </el-col>





                            </el-form>

                        </div>
                    </el-col>
                </el-row>
            </el-dialog>
        </div>

    </div>
</template>
<script>
    export default {
        data(){
            return{
                preVisiable:false,
                condition: {
                    id: "",
                        address: "",
                        category: [],
                        city: "",
                        contact: "",
                        county: "",
                        courierCompany: "",
                        courierNumber: "",
                        creatTime: [],
                        getToken: "",
                        kind: [],
                        orderSn: "",
                        orderStatus: [],
                        payId: "",
                        payIntegral: [],
                        payPrice: [],
                        payTime: [],
                        phone: "",
                        postage: [],
                        province: "",
                        receiptTime: [],
                        storeId: [],
                        tokenTime: [],
                        userId: [],
        },
            xuhao:true,
                    dicDisabled:true,
                    pageData: {
                list: [],
                        pageNumber: 1,
                        pageSize: 10,
                        totalCount: 0,
                        totalPage: 0
            },
            multipleSelection:[],//选中的数据集合
                    form :{
                    address: "",
                    category: "",
                    city: "",
                    contact: "",
                    county: "",
                    courierCompany: "",
                    courierNumber: "",
                    creatTime: "",
                    getToken: "",
                    id: "",
                    kind: "",
                    orderSn: "",
                    orderStatus: "",
                    payId: "",
                    payIntegral: "",
                    payPrice: "",
                    payTime: "",
                    phone: "",
                    postage: "",
                    province: "",
                    receiptTime: "",
                    storeId: "",
                    tokenTime: "",
                    userId: "",
            },
            editVisible:false,
            rules: {
                address: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                category: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                city: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                contact: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                county: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                courierCompany: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                courierNumber: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                creatTime: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                getToken: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                kind: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                orderSn: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                orderStatus: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                payId: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                payIntegral: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                payPrice: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                payTime: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                phone: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                postage: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                province: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                receiptTime: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                storeId: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                tokenTime: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                userId: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],

            },
            queryVisible:{
                        address:false,
                        category:false,
                        city:false,
                        contact:false,
                        county:false,
                        courierCompany:false,
                        courierNumber:false,
                        creatTime:false,
                        getToken:false,
                        kind:false,
                        orderSn:false,
                        orderStatus:false,
                        payId:false,
                        payIntegral:false,
                        payPrice:false,
                        payTime:false,
                        phone:false,
                        postage:false,
                        province:false,
                        receiptTime:false,
                        storeId:false,
                        tokenTime:false,
                        userId:false,
            },
            checkBoxList:{
                                categoryList:[],
                                kindList:[],
                                orderStatusList:[],
                                storeIdList:[],
                                userIdList:[],
            },
            pickerOptions: {
                shortcuts: [
                    {
                        text: "最近一周",
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit("pick", [start, end]);
                        }
                    },
                    {
                        text: "最近一个月",
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit("pick", [start, end]);
                        }
                    },
                    {
                        text: "最近三个月",
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit("pick", [start, end]);
                        }
                    }
                ]
            }
        }
        },

        created(){
            this.getdata();
        },
        methods:{
            //新增
            handleAdd(){
                this.editVisible=true;
                this.empty();
            },
            //批量删除--不需要批量删除请将其注释，，
            handleDeleteBatch(){
                let deletebatch = [];
                this.multipleSelection.forEach(i => {
                    deletebatch.push(i.id);
            });
                this.$confirm("此操作将永久删除, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                })
                        .then(() => {
                    this.$http .post(this.api.appOrderBatchDelete, deletebatch).then(res => {
                        if (res.data.code == "200") {
                    this.$message({
                        message: "批量删除数据成功",
                        type: "success"
                    });
                }
                this.getdata();
            });
            })
            .catch(() => {
                    this.$message({
                        type: "info",
                        message: "已取消批量删除"
                    });
            });

            },
            //查询
            search(){
                this.getdata();
            },
            //重置
            reset(){
                this.empty();
                this.pageData.pageNumber=1;
                this.pageData.pageSize=10;
                this.getdata();
            },
            //勾选数据集合
            handleSelectionChange(val){
                if (val.length > 0) {
                    this.multipleSelection = val;
                    this.dicDisabled = false;
                } else {
                    this.dicDisabled = true;
                }
            },
            //预览
            handlePre(row){
                this.editVisible=true;
                this.preVisiable=true
                this.form=row;

            },
            //编辑
            handleEdit(row){
                this.editVisible=true;
                this.preVisiable=false
                this.form=row;
            },
            //单个删除
            handleDelete(id){
                this.$confirm("此操作将永久删除, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                })
                        .then(() => {
                    this.$http
                    .delete(this.api.appOrderDeletebyid + id).then(res => {
                        if (res.data.code == "200") {
                    this.$message({
                        message: "删除数据成功",
                        type: "success"
                    });
                }
                this.getdata();
            });
            })
            .catch(() => {
                    this.$message({
                        type: "info",
                        message: "已取消删除"
                    });
            });
            },
            //页数
            handlePage(number, size){
                this.pageData.pageNumber=number;
                this.pageData.pageSize=size;
                this.getdata();
            },
            //表格数据
            getdata(){
                this.getxiala();
                this.getTableData();
            },
            //保存/修改
            handleSave(){
                this.$refs
                        .form.validate(valid => {if (valid) {

        if(!!this.form.creatTime){
            var d=this.form.creatTime;
            this.form.creatTime=this.dateUtils(d);
        }
        if(!!this.form.payTime){
            var d=this.form.payTime;
            this.form.payTime=this.dateUtils(d);
        }
        if(!!this.form.receiptTime){
            var d=this.form.receiptTime;
            this.form.receiptTime=this.dateUtils(d);
        }
        if(!!this.form.tokenTime){
            var d=this.form.tokenTime;
            this.form.tokenTime=this.dateUtils(d);
        }
                        this.$http
                                .post(this.api.appOrderSaveOrUpdate, this.form).then(res=> {
                            if (res.data.code == "200") {
                            this.$message
                                    .success(res.data.message);
                            this.editVisible = false;
                            this.empty();
                            this.getdata();
                        }else{
                            this.$message
                                    .error(res.data.message);
                        }
                    });
                    }
                })
            },
            cancelSave(){
                this.editVisible=false;
            },
            empty(){
                this.form={
                    address: "",
                    category: "",
                    city: "",
                    contact: "",
                    county: "",
                    courierCompany: "",
                    courierNumber: "",
                    creatTime: "",
                    getToken: "",
                    id: "",
                    kind: "",
                    orderSn: "",
                    orderStatus: "",
                    payId: "",
                    payIntegral: "",
                    payPrice: "",
                    payTime: "",
                    phone: "",
                    postage: "",
                    province: "",
                    receiptTime: "",
                    storeId: "",
                    tokenTime: "",
                    userId: "",
            };
                this.condition= {
                    id: "",
                            address: "",
                            category: [],
                            city: "",
                            contact: "",
                            county: "",
                            courierCompany: "",
                            courierNumber: "",
                            creatTime: [],
                            getToken: "",
                            kind: [],
                            orderSn: "",
                            orderStatus: [],
                            payId: "",
                            payIntegral: [],
                            payPrice: [],
                            payTime: [],
                            phone: "",
                            postage: [],
                            province: "",
                            receiptTime: [],
                            storeId: [],
                            tokenTime: [],
                            userId: [],
            }
            },

            //获取下拉数据
            getxiala(){
                //下拉数据模板
                // this.$http
                //        .get(this.api.dicTypeGetType + "orderStatus").then(res => {
                //         if (res.data.code == 200) {
                //             this.orderStatusList = res.data.body.map(item => ({
                //                 label: item.label,
                //                 value: parseInt(item.value),
                //                 checked: false
                //             }));;
                // }
                // });

                // this.$http
                //     .post(this.api.appUserQueryByCondition, {
                //         condition:  {},
                //         number: 1,
                //         size: 99999
                //     })
                //     .then(res => {
                //     if (res.data.code == "200") {
                //     this.checkBoxList.parendIdList=res.data.body.records.map(item => ({
                //         label: item.storeName,
                //         value: item.id,
                //         checked: false
                //     }));
                //     this.checkBoxList.userIdList=this.checkBoxList.parendIdList;

                //     }
                // });

                    appOrderQueryByCondition: `/moban/api/linxing/appOrder/querybycondition`,//查询
                    appOrderDeletebyid: `/moban/api/linxing/appOrder/deleteById/`,//单个删除
                    appOrderBatchDelete: `/moban/api/linxing/appOrder/batchdelete`,//批量删除
                    appOrderSaveOrUpdate: `/moban/api/linxing/appOrder/saveOrUpdate`,//更新或新增
                    appOrderListFieldQuery: `/moban/api/linxing/appOrder/listFieldQuery`,//列表查询
            },

            //获取数据
            getTableData(){
                this.$http
                        .post(this.api.appOrderListFieldQuery, {
                            ...this.condition,
                            number: this.pageData.pageNumber,
                            size: this.pageData.pageSize
                        })
                        .then(res => {
                    if (res.data.code == "200") {
                    this.pageData.list = res.data.body.records;
                    this.pageData.totalCount = res.data.body.total;
                    this.pageData.totalPage = res.data.body.pages;
                }
            });
            },

            //时间转换器---start
            dateUtils(d){
                var year=d.getFullYear();
                var month=this.prefixInteger(d.getMonth());
                var date=this.prefixInteger(d.getDate());
                var hours=this.prefixInteger(d.getHours());
                var minutes=this.prefixInteger(d.getMinutes());
                var seconds=this.prefixInteger(d.getSeconds());
                return year + '-' + month + '-'  + date + ' '  + hours + ':'  + minutes + ':'  + seconds;
            },

            //自动补零
            prefixInteger(num) {
                if(num>9){
                    return num;
                }
                return (Array(2).join(0) + num).slice(-2);
            },
            //时间转换器---end

            //普通查询及取消
            handleSimpleFilterOk(filterName){

                if(!this.condition[filterName]){
                    return this.$message.error("未输入查询内容")
                }
                this.queryVisible[filterName] = false;
                this.getTableData();
            },
            handleSimpleFilterCancel(filterName){
                this.condition[filterName] = "";
                this.queryVisible[filterName] = false;
                this.getTableData();
            },
            //数字框查询及取消
            handleNumFilterOk(filterName){

                if(this.condition[filterName].length==0){ return this.$message.error("未填入查询数字")}
                if(!this.condition[filterName][0]||!this.condition[filterName][1]){ return this.$message.error("未填入查询数字")}

                this.queryVisible[filterName] = false;
                this.getTableData();
            },
            handleNumFilterCancel(filterName){
                this.condition[filterName] = [];
                this.queryVisible[filterName] = false;
                this.getTableData();
            },

            //多选框查询及取消
            handleCheckboxListFilterOk(filterName) {

                var aa=[];
                this.checkBoxList[filterName+"List"].forEach(v => {
                    if(v.checked){
                    aa.push(v.value)
                }
            });
                if(aa.length==0){ return this.$message.error("未勾选查询选框")}
                this.condition[filterName]=aa;
                this.queryVisible[filterName] = false;
                this.getTableData();
            },
            handleCheckboxListFilterCancel(filterName) {
                this.checkBoxList[filterName+"List"].forEach(item => (item.checked = false));
                this.condition[filterName]=[];
                this.queryVisible[filterName] = false;
                this.getTableData();
            },
            //日期查询
            handleDateRangeListFilterOk(filterName) {

                const [minDate, maxDate] = this.condition[filterName];
                this.condition[filterName] = [
                    minDate.replace(/(\s.+)$/, " 00:00:00"),
                    maxDate.replace(/(\s.+)$/, " 23:59:59")
                ];
                this.queryVisible[filterName] = false;
                console.log(this.queryVisible.unfreezeTime);
                this.getTableData();
            },
            handleDateRangeListFilterCancel(filterName) {
                this.condition[filterName] = [];
                this.queryVisible[filterName] = false;
                this.getTableData();
            },



        },

    }
</script>
<style lang="scss" scope>
    //表头高度调整
    .el-table__header th {
        padding: 0;
        height: 50px ;
    }
    // 弹出框样式
       .showDialog .el-dialog__body{
           padding: 5px 20px;
       }
    .red{
        color: rgb(243, 81, 94);
    }
    .table{
        height:100%;
        width: 100%;
    }
    .icon-filter {
        display: inline-block;
        height: 34px;
        width: 24px;
        vertical-align: middle;
        cursor: pointer;
        background-image: url("../../../assets/img/souSuo.png");
        background-size:20px 20px;
        background-repeat: no-repeat;
        background-position: center;
    }
</style>