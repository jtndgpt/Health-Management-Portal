function calculateBMR(){
    var weight=document.getElementById("weight").value?document.getElementById("weight").value:0;
    var height=document.getElementById("height").value?document.getElementById("height").value:0;
    var age=document.getElementById("age").value?document.getElementById("age").value:0;
    var gender;
    if(document.getElementById("female").checked){
        gender="female";
    }
    else {
        gender="male";
    }   
    $.ajax({
        url:'CalculateBmrPath',
        type:'POST',
        cache:false,
        data:{weight: weight, height: height, age: age, gender: gender},
        success: function(totalBmr){
            $("#bmr").val(totalBmr);
        }
    });
}

function calculateTCR(){
    var bmr=document.getElementById("bmr").value;
    if(bmr==""){
        bmr = 0;
    }
    var exerciseLevel;
    if(document.getElementById("1").checked){
            exerciseLevel = 1.2;
    }
    else if(document.getElementById("2").checked){
        exerciseLevel = 1.375;
    }
    else if(document.getElementById("3").checked){
        exerciseLevel = 1.55;
    }
    else if(document.getElementById("4").checked){
        exerciseLevel = 1.725;
    }
    else if(document.getElementById("5").checked){
        exerciseLevel = 1.9;
    }
    else{
        exerciseLevel = 1.0;
    }
    $.ajax({
        url:'CalculateTcrPath',
        type:'POST',
        cache:false,
        data:{bmr: bmr, exerciseLevel: exerciseLevel},
        success: function(totalTcr){
            $("#tcr").val(totalTcr);
        }
    });
}

var proteinSelected, grainSelected, veggeisSelected, fruitSelected, dairySelected;
function displayProtain(){
	proteinSelected=document.getElementById("protain").value;
        $.ajax({
            url:'selectFoodPath',
            type:'POST',
            cache:false,
            data:{foodSelected: proteinSelected, choice: 1},
            success: function(proteinCalories){
                $("#protainCalories").html(proteinCalories);
            }
        });
}
function displayGrain(){
	grainSelected=document.getElementById("grain").value;
        $.ajax({
            url:'selectFoodPath',
            type:'POST',
            cache:false,
            data:{foodSelected: grainSelected, choice: 0},
            success: function(grainCalories){
                $("#grainCalories").html(grainCalories);
            }
        });
}
function displayVeggies(){
	veggeisSelected=document.getElementById("veggies").value;
        $.ajax({
            url:'selectFoodPath',
            type:'POST',
            cache:false,
            data:{foodSelected: veggeisSelected, choice: 2},
            success: function(veggiesCalories){
                $("#veggiesCalories").html(veggiesCalories);
            }
        });
}
function displayFruit(){
	fruitSelected=document.getElementById("fruit").value;
        $.ajax({
            url:'selectFoodPath',
            type:'POST',
            cache:false,
            data:{foodSelected: fruitSelected, choice: 3},
            success: function(fruitCalories){
                $("#fruitCalories").html(fruitCalories);
            }
        });
}
function displayDairy(){
	dairySelected=document.getElementById("dairy").value;
        $.ajax({
            url:'selectFoodPath',
            type:'POST',
            cache:false,
            data:{foodSelected: dairySelected, choice: 4},
            success: function(dairyCalories){
                $("#dairyCalories").html(dairyCalories);
            }
        });
}

function calculateRDA(){
    $("#error_msg").css("display","none");                
    var tcr=document.getElementById("tcr").value;
    if(tcr==""){
        tcr = 0;
    }
    $.ajax({
        url:'CalculateRdaPath',
        type:'POST',
        cache:false,
        data:{tcr: tcr, proteinSelected: proteinSelected, grainSelected: grainSelected, veggeisSelected: veggeisSelected, fruitSelected: fruitSelected, dairySelected: dairySelected},
        success: function(totalRda){
            var temp=JSON.parse(totalRda);
            if(temp.status){                
                $("#proteinFinal").val(temp.protein);
                $("#grainFinal").val(temp.grains);
                $("#veggiesFinal").val(temp.veggies);
                $("#fruitFinal").val(temp.fruits);
                $("#dairyFinal").val(temp.dairy);
            }            
            else{
                $("#error_msg").html(temp.err_msg);
                $("#error_msg").css("display","block");                
            }
        }
    });
}