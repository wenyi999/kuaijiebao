
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
                <Button type="primary" style={{marginLeft:"40%"}}> 在线咨询</Button>
                </div>
        </Collapse>)



    }
}

export default Help;




