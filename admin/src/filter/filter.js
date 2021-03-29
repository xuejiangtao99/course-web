let optionKeyArray = (list,key) =>{

    let result = "";
    if(!list || !key){
        return "";
    }else{
        //SECTION_CHARGE={CHARGE:{key:'C',value:'收费'},FREE:{key:'F',value:'免费'}}
        for (let i = 0; i < list.length; i++) {
            console.log(list[i]['key'])
            if(list[i]['key'] === key){
                result=list[i]['value']
            }
        }
    }
    return result;
}


/**
 * 数组过滤器 例如：{{CHARGE | optionKVArray(section.charge)}}
 * @param list 例如：[{key:"C", value:"收费"},{key:"F", value:"免费"}]
 * @param key 例如：C
 * @returns {string} 例如：收费
 */
let optionKVArray = (list, key) =>  {
    if (!list || !key) {
        return "";
    } else {
        let result = "";
        for (let i = 0; i < list.length; i++) {
            if (key === list[i]["key"]) {
                result = list[i]["value"];
            }
        }
        return result;
    }
};

export default {
    optionKeyArray,
    optionKVArray
}