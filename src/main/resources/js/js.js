const main = document.querySelector('.container'); 
const author ={
    name:"Bolat",
    surName:"Musylmanbecov",
    competention :["UX/UI Designer", "Web Developer"],
    github:"https://github.com/Bart22422?tab=repositories",
    telegram:"https://t.me/BartWallker",
    email:"bolatmusylmanbekov@gmail.com"
}
const header =document.createElement('div');
const buttonsBlock=document.createElement('div');
    buttonsBlock.className="buttons_block"
const head = document.createElement('h1');
    head.textContent = author.name+" "+author.surName;
    
const subLine = document.createElement('div');
competentionLoop(author.competention,subLine);
    
const primary = document.createElement('button');
primary.textContent="portfolio"
primary.className="primary btn"
let classNames = "btn";
if(primary.classList.contains('primary')){
    primary.style.color="#021ebb";
}
const btn = document.createElement('button');
btn.style.cssText="#ffffff";

btn.style.color="white";
btn.textContent="about";
btn.className=classNames;
const contentText = document.createElement('div');
    contentText.className="content_text";
const br = document.createElement('br');
const footer = document.createElement('div');
buttonsBlock.append(primary,btn)
header.append(head,subLine,buttonsBlock)
footer.innerHTML="<p>Musylmanbecov Bolat</p><p>bolatmusylmanbekov@gmail.com</p><p>+7(999)-173-26-28</p>"
footer.className="footer"
main.append(header,br,contentText,footer);
btn.onclick=()=>{
    contentText.innerHTML="";
    const aboutBlock = document.createElement('div');
        aboutBlock.className="about_block";
   
    const github=document.createElement('p');
        github.textContent="GitHub: "
    const telegram=document.createElement('p');
        telegram.textContent="Telegram: "
    const email=document.createElement('p');
        email.textContent="email: "
    const githubLink=document.createElement('a');
        githubLink.href=author.github;
        githubLink.textContent="Bart22422";
    const telegramLink=document.createElement('a');
        telegramLink.href=author.telegram;
        telegramLink.textContent="@BartWallker";
    const emailLink=document.createElement('a');
        emailLink.href=author.email;
        emailLink.textContent=author.email;
    aboutBlock.append(github,githubLink,telegram,telegramLink,email,emailLink);
    contentText.append(aboutBlock);
}
primary.onclick=()=>{
    contentText.innerHTML="";
    fetch('http://localhost:8080/projectList')
    .then(response=>response.json())
    .then(data=>{
        const projectCountMessage = document.createElement('p');
            projectCountMessage.className="tag"
			
            projectCountMessage.textContent=data.length+" Projects:"
            contentText.append(projectCountMessage);
        data.forEach(element => {
            const portfolioBlock = document.createElement('div');
            portfolioBlock.className="portfolio_block";
            const image=document.createElement('img');
            const portfolioTextBlock = document.createElement('div')
            portfolioTextBlock.className="portfolio_text_block"
            const name=document.createElement('h2');
            const description=document.createElement('p');
            const tagsCount=element.tags.length;
            const tags=document.createElement('div');
                tags.className="tags"
            for(let i = 0; i<tagsCount;i++){
                const tag = document.createElement('p')
                tag.textContent=element.tags[i];
                tag.className="tag";
                tags.append(tag);
            }
            const url=document.createElement('a')
            image.src='http://localhost:8080/image/'+element.id
            image.className="image"
            name.textContent=element.name;
            description.textContent=element.description;
            url.textContent="Link";
            url.href=element.url;
            portfolioTextBlock.append(name,description,tags,url)
            portfolioBlock.append(image,portfolioTextBlock)
            contentText.append(portfolioBlock)
        })
    })

}
    

function competentionLoop (c, div){
    const active = true
    const text = document.createElement('p')
    div.append(text);
    let i=0;
    
    function loop (){
        if(i>c.length-1){
            i=0;
        }
        if(active==true){
            let arr = c[i].split('');
            let string ='';
            let k = 0, howManyTimes = arr.length;
            function f() {
            
                string+=arr[k]
                text.textContent=string
                k++;
                if( k < howManyTimes ){
                    setTimeout( f, 100 );
                }
            }
            f();
            i++;
            setTimeout(loop, 3000)
        }
    } 
    loop();
}