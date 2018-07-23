
import React, {Component} from 'react';
import { Collapse,Button } from 'antd';
const Panel = Collapse.Panel;

const text1 = `
  解决方案1
  
`;

const text2 = `
  解决方案2
  
`;
const text3 = `
  解决方案3
  
`;

class Help extends Component {
    render() {
        return(
            <Collapse accordion>
            <Panel header="问题1" key="1">
                <p>{text1}</p>
            </Panel>
            <Panel header="问题2" key="2">
                <p>{text2}</p>
            </Panel>
            <Panel header="问题3" key="3">
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




