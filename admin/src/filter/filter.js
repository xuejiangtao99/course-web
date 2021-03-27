let optionKeyArray = (list,key) =>{

    let result = "";
    if(!list || !key){
        return "";
    }else{
        for (let i = 0; i < list.length; i++) {
            if(list[i]['key'] === key){
                result=list[i]['value']
            }
        }
    }
    return result;
}


export default {
    optionKeyArray
}