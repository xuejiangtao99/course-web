LOCAL_KEY_USER_REMEMBER = "LOCAL_KEY_USER_REMEMBER"

LocalStorage = {
    get:function (key){
        let v = localStorage.getItem(key)
        if(v && typeof (v) !== 'undefined' && v !== 'undefined'){

            return JSON.parse(v)
        }
    },
    set:function (key,v){
        localStorage.setItem(key,JSON.stringify(v))
    },
    remove:function (key){
        localStorage.removeItem(key)
    },
    clearAll:function (){
        localStorage.clear()
    }
}