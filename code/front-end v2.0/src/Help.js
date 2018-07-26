
import React, {Component} from 'react';
import { Collapse,Button } from 'antd';
const Panel = Collapse.Panel;

const text1 = `
  在个人中心的个人信息点击申请额度可以申请提高自己的初始额度；
  多进行出借，借款，购买理财商品等方式可以提高自己的信用额度。
  
`;

const text2 = `
  一天之内
  
`;
const text3 = `
  这取决于你的借款条件，如果有人愿意借给你，你的借款需求就能马上完成
  
`;

class Help extends Component {
    render() {
        return(
            <Collapse accordion>
            <Panel header="我怎么提高自己的信用额度" key="1">
                <p>{text1}</p>
            </Panel>
            <Panel header="我申请的信用额度一般多久可以得到审批" key="2">
                <p>{text2}</p>
            </Panel>
            <Panel header="我申请的借款什么时候可以到" key="3">
                <p>{text3}</p>
            </Panel>


                <div>
                        <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2947711395&site=qq&menu=yes">
                        <img border="0" src="http://wpa.qq.com/pa?p=2:2947711395:53" alt="在线QQ咨询" title="在线QQ咨询"/></a>
                </div>
        </Collapse>)



    }
}

export default Help;




