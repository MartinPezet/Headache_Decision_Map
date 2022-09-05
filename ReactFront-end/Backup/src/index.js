import React from 'react';
import ReactDOM from 'react-dom';
import './style.css';
import $ from 'jquery';

// ============================ Body ============================

class Body extends React.Component {

    // ----- Methods for Requests -----

    render() {
        return(
            <div className='height'>
                <div className='Body'>

                    <Title/>

                    <Boxes />

                </div>
            </div>
        );
    }
}

// ============================ Title ============================

class Title extends React.Component {
    render() {
        return(
            <div>
                <h1 id='Title'>What headache do I have?</h1>
            </div>
        );
    }
}

// ============================ Boxes ============================

class Boxes extends React.Component {
    constructor(props){
        super(props);

        this.componentDidMount = this.componentDidMount.bind(this);
        this.send = this.send.bind(this);
        this.toggleButton = this.toggleButton.bind(this);
        this.processRequestNode = this.processRequestNode.bind(this);
        this.startRequest = this.startRequest.bind(this);

        this.state = {
            getResponse: null,
            currentNode: {
                nodeNumber: '0',
                infoText: 'Failure to connect to Java Servlet',
                questionText: '-',
            },
            button: {
                active: '',
                YesClass:'button',
                NoClass:'button',
            },
            infoRender: false,
            questionRender: false,
            submitRender: false,
            restartRender: false,
            startRender: true,
        }

    }

    componentDidMount(){
        this.setState({
            button: {
                active: '',
                YesClass:'button',
                NoClass:'button',
            },
        });
    }

    toggleButton(name) {
        if (this.state.button.active === name) {return(null);}
        if (this.state.button.active === ''){
            if (name === 'Yes'){
                this.setState({button: {YesClass: 'button active', NoClass: 'button',  active: 'Yes',}})
            } else {
                this.setState({button: {YesClass: 'button', NoClass: 'button active', active: 'No',}})
            }
        } else {
            this.setState({button: {active: name,}})
            if (name === 'Yes'){
                this.setState({button: { YesClass: 'button active', NoClass: 'button',  active: 'Yes',}})
            } else {
                this.setState({button: {YesClass: 'button', NoClass: 'button active',  active: 'No',}})
            }
        }
    }

    send() {
        if(this.state.questionRender === true){
            if(this.state.button.active === 'Yes' || this.state.button.active === 'No'){
                if (this.state.button.active === 'Yes'){
                    this.yesRequest();
                } else {
                    this.noRequest();
                }
            } else {
                alert("HINT: Press yes or no!");
            }
            this.setState({
                button: {
                    active: '',
                    YesClass:'button',
                    NoClass:'button',
                }
            });
        } else {
            this.yesRequest();
        }
    }

    checkNode() {
        if(this.state.currentNode.nodeNumber === '4'){
            this.setState({
                infoRender: false,
                questionRender: false,
                submitRender: false,
                restartRender: true,
            });
            return;
        }
        else {
            this.setState({submitRender:true ,})
            if(this.state.currentNode.infoText === '-'){
                this.setState({infoRender: false,});
            } else {
                this.setState({infoRender: true,});
                console.log(this.state.currentNode.infoText)
                //var String = '' + this.state.currentNode.intoText;
                this.setState({ currentNode:{
                        nodeNumber: this.state.currentNode.nodeNumber,
                        infoText: this.checkText(this.state.currentNode.infoText),
                        questionText: this.state.currentNode.questionText,
                    }
                });
            }
            if(this.state.currentNode.questionText === '-'){
                this.setState({questionRender: false,});
            } else {
                this.setState({questionRender: true,});
                console.log()
                //var str = '' + this.state.currentNode.questionText;
                this.setState({ currentNode:{
                    nodeNumber: this.state.currentNode.nodeNumber,
                    infoText: this.state.currentNode.infoText,
                    questionText: this.checkText(this.state.currentNode.questionText),
                    }
                });
            }
        }

    }

    checkText(String) {
        console.log(String);
        var strArray = String.split(' ');
        var str = '';
        for (let i in strArray){
            if (strArray[i] === "|"){
                strArray[i - 1] = strArray[i - 1] + ",";
                strArray[i] = '';
            } 
            if (strArray[i] === 'https://www.mayoclinic.org/diseases-conditions/primary-cough-headaches/symptoms-causes/syc-20371200' || strArray[i] === 'https://www.nhs.uk/conditions/tension-headaches/' || strArray[i] === 'https://www.news-medical.net/health/What-is-Muscle-Contraction-Headache.aspx' || strArray[i] === 'https://www.nhs.uk/conditions/migraine/symptoms/' || strArray[i] === 'https://www.nhs.uk/conditions/cluster-headaches/' || strArray[i] === 'https://americanmigrainefoundation.org/resource-library/parosyxmal-hemicrania/' || strArray[i] === 'primary-cough-headaches/symptoms-causes/syc-20371200' || strArray[i] === 'https://americanmigrainefoundation.org/resource-library/ice-pick-headaches/' || strArray[i] === 'https://americanmigrainefoundation.org/resource-library/orgasmic-pre-orgasmic-headache/' || strArray[i] === 'https://www.mayoclinic.org/diseases-conditions/thunderclap-headaches/symptoms-causes/syc-20378361' || strArray[i] === 'https://www.migrainetrust.org/about-migraine/types-of-migraine/other-headache-disorders/hypnic-headache/' || strArray[i] === 'https://www.migrainetrust.org/about-migraine/types-of-migraine/other-headache-disorders/hemicrania-continua/' || strArray[i] === 'https://www.healthline.com/health/headache/new-daily-persistent-headache' || strArray[i] === 'https://www.mayoclinic.org/diseases-conditions/exercise-headaches/symptoms-causes/syc-20372276' || strArray[i] === 'https://www.nhs.uk/conditions/trigeminal-neuralgia/symptoms/') {
                strArray[i] = '<a href="'+ strArray[i] +'" target="_blank" rel="noreferrer">' + strArray[i] + '</a>'
            }
        }
        for (let i in strArray){
            if (strArray[i] !== ""){
                str = str + " " + strArray[i];
            }
        }
        return str;
    }

    processRequestNode(response) {
        const res = response;
        var answerArray = res.split(', ');
        if (answerArray.length < 3){ console.log("Null error from servlet"); return; }
        for (let i = 0; i < answerArray.length; i++) {
            answerArray[i] = answerArray[i].split("=");
        }

        this.setState(
            {currentNode: {
                nodeNumber: answerArray[0][1],
                infoText: answerArray[1][1],
                questionText: answerArray[2][1],
            }}
        );

        this.checkNode();
    }

    startRequest() {
        var self = this;
        var urlEndPoint = 'http://localhost:8080/DecisionMapServlet_war_exploded/StartMapServlet';

        this.setState({
            infoRender: false,
            questionRender: false,
            submitRender: false,
            restartRender: false,
            startRender: false,
        });

        $.get({
            type: "GET",
            url: urlEndPoint,
            success: function(response) {
                self.processRequestNode(response);
                //alert('Success ' + response)
            },
            error: function(response) {
                self.setState({getResponse: response});
                self.checkNode();
                console.log("Failed start request")
                return null;
                //alert(response)
            }
        });

    }

    yesRequest() {
        var self = this;
        var node = self.state.currentNode.nodeNumber;
        console.log("Current Node: " + node)
        var urlEndPoint = 'http://localhost:8080/DecisionMapServlet_war_exploded/YesNodeServlet';

        $.get({
            type: "GET",
            url: urlEndPoint,
            data: {num: node},
            success: function(response) {
                self.processRequestNode(response);
                //alert('Success', response)
            },
            error: function(response) {
                self.setState({ServletGetResponse: response})
                self.checkNode();
                console.log("Failed yes request")
            }
        });

    }

    noRequest(){
        var self = this;
        var node = self.state.currentNode.nodeNumber;
        console.log("Current Node: " + node);
        var urlEndPoint = 'http://localhost:8080/DecisionMapServlet_war_exploded/NoNodeServlet';

        $.get({
            type: "GET",
            url: urlEndPoint,
            data: {num: node},
            success: function(response) {
                self.processRequestNode(response);
                //alert('Success', response)
            },
            error: function(response) {
                self.setState({ServletGetResponse: response})
                self.checkNode();
                console.log("Failed no request")
            }
        });

    }

    render() {
        return(
            <div>
                {this.state.infoRender ? <InfoBox infoText={this.state.currentNode.infoText} /> : null}

                {this.state.questionRender ? <QuestionBox questionText={this.state.currentNode.questionText} YesClass={this.state.button.YesClass} NoClass={this.state.button.NoClass} onClickYes={() => this.toggleButton('Yes')} onClickNo={() => this.toggleButton('No')}/> : null}

                {this.state.submitRender ? <SubmitButton onClick={() => this.send()}/> : null}

                {this.state.restartRender ? <RestartButton onClick={() => this.startRequest()} /> : null}

                {this.state.startRender ? <StartButton onClick={() => this.startRequest()} /> : null}
            </div>
        );
    }
}

// ============================ Info Box ============================

class InfoBox extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            infoText: props.infoText,
        }
    }

    UNSAFE_componentWillReceiveProps(nextProps){
        this.setState({
            infoText: nextProps.infoText,
         });
    }

    render() {
        return(
            <div className='box' id='information'>
                <InfoText text={this.state.infoText}/>
            </div>
        );
    }
}

// ============================ Info Text ============================

class InfoText extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            text: props.text,
        }
    }

    UNSAFE_componentWillReceiveProps(nextProps){
        this.setState({
            text: nextProps.text,
        });
    }

    render() {
        return(
            <div className='boxText'>
                <p dangerouslySetInnerHTML={{__html: this.state.text,}}></p>
            </div>

        );
    }
}

// ============================ Question Box ============================

class QuestionBox extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            questionText: props.questionText,
            button: {
                YesClass: props.YesClass,
                NoClass: props.NoClass,
                onClickYes: props.onClickYes,
                onClickNo: props.onClickNo,
            }
        }
    }

    UNSAFE_componentWillReceiveProps(nextProps){
        this.setState({
            questionText: nextProps.questionText,
            button: {
                YesClass: nextProps.YesClass,
                NoClass: nextProps.NoClass,
                onClickYes: nextProps.onClickYes,
                onClickNo: nextProps.onClickNo,
            }
        });
    }

    render() {
        return(
            <div className='box' id='question'>

                <QuestionText text={this.state.questionText} />

                <Buttons YesClass={this.state.button.YesClass} NoClass={this.state.button.NoClass} onClickYes={this.state.button.onClickYes} onClickNo={this.state.button.onClickNo} />

            </div>
        );
    }
}

// ============================ Question Text ============================

class QuestionText extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            text: props.text,
        }
    }

    UNSAFE_componentWillReceiveProps(nextProps){
        this.setState({
            text: nextProps.text,
        });
    }

    render() {
        return(
            <div className='boxText'>

                <p dangerouslySetInnerHTML={{__html: this.state.text,}}></p>

            </div>
        );
    }
}

// ============================ Buttons ============================

class Buttons extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            YesClass: props.YesClass,
            NoClass: props.NoClass,
            onClickYes: props.onClickYes,
            onClickNo: props.onClickNo,
        }

    }

    UNSAFE_componentWillReceiveProps(nextProps){
        this.setState({
            YesClass: nextProps.YesClass,
            NoClass: nextProps.NoClass,
            onClickYes: nextProps.onClickYes,
            onClickNo: nextProps.onClickNo,
        });
    }

    render() {
        return(
            <div className='buttons'>
                <div className='buttonContainer'>
                    <button className={this.state.YesClass} onClick={this.state.onClickYes}>Yes</button>
                </div>
                <div className='buttonContainer'>
                    <button className={this.state.NoClass} onClick={this.state.onClickNo}>No</button>
                </div>
            </div>
        );
    }
}

// ============================ Submit Button ============================

class SubmitButton extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            onClick: props.onClick,
        }
    }

    render() {
        return(
            <div className='submitContainer'>
                <button className='commonButton submit' onClick={this.state.onClick}>Next</button>
            </div>
        );
    }
}

// ============================ Restart Button ============================

class RestartButton extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            onClick: props.onClick,
        }
    }

    render() {
        return(
            <div>
                <button className='commonButton middleButton restart' onClick={this.state.onClick}>Restart</button>
            </div>
        );
    }
}

// ============================ Start Button ============================

class StartButton extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            onClick: props.onClick,
        }
    }

    render() {
        return(
            <div>
                <button className='commonButton middleButton start' onClick={this.state.onClick}>Start</button>
            </div>
        );
    }
}

// ============================ Footer Copyright ============================

class Footer extends React.Component {
    render() {
        return(
            <div className='FooterContainer'>
                <p className='Footer'>Copyright © Martin Pezet(UP922969)</p>
            </div>
        );
    }
}

// Container

class Container extends React.Component {
    render(){
        return(
            <div className='container'>
                <Body />

                <Footer />
            </div>
        );
    }
}

// DOM render

ReactDOM.render(
    <Container />,
    document.getElementById('root')
  );