var message=[];
var messageRead=[];

function run(){
	 var appContainer = document.getElementsByClassName('todos content')[0];
	 appContainer.addEventListener('click', delegateEvent);
       messageRead=loadMessages();
	   

  message.push.apply(message,messageRead) ; 
   for(var i=0; i<messageRead.length; i++) {
		 addTodo(messageRead[i].author,messageRead[i].description); 	
}
}

function loadMessages() {
	if(typeof(Storage) == "undefined") {
		alert('localStorage is not accessible');
		return;
	}

	var item = localStorage.getItem("TODOs taskList");

	return item && JSON.parse(item);
}


function delegateEvent(evtObj) {	
	if(evtObj.type==='click' && evtObj.target.classList.contains('send-button')){
		var login=document.getElementsByName ('login')[0];
		var todoText=document.getElementsByClassName ('mail-input')[0];
		if(login.value!=''){
		onAddButtonClick(login.value,todoText.value);
		}
	}
	if(evtObj.type==='click' && evtObj.target.classList.contains('name-button')){
	var login=document.getElementsByName ('login')[0];
	if(login.value!=''){
		onAddButtonClick(login.value,'New User! ');
	}
	}
}

function onAddButtonClick(login,todoText){
	
	message.push(newMessage(login,todoText));  
	addTodo(login,todoText);
	todoText.value='';
	saveMessages(message);
}

function addTodo(login,value){
	if(!value){
		return;
	}
	var item=createText(login,value);
	var items=document.getElementsByClassName("todo-output-text")[0];
	items.appendChild(item);
}

function createText(login,text){
	
	var divItem=document.createElement('div');
	divItem.classList.add('message');
	divItem.appendChild(document.createTextNode(login+':'+text));
	return divItem;
}

function saveMessages(listToSave) {
	if(typeof(Storage) == "undefined") {
		alert('localStorage is not accessible');
		return;
	}
	localStorage.setItem("TODOs taskList", JSON.stringify(listToSave));
}

function uniqueId() {
	var date = Date.now();
	var random = Math.random() * Math.random();

	return Math.floor(date * random);
}

function newMessage(author,text) {
	return {
		author: author,
		description:text,	
		id: '' + uniqueId()
	};
}