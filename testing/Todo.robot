*** Settings ***
Library           SeleniumLibrary

*** Variables ***
${URL}            file:///D:/Assignment%203/Assignment%203/Software-Testing-Assi3-Frontend/todo.html
${Submit}         xpath=//*[@id="todo-form"]/button
${Delete1}        xpath=//*[@id="row-1"]/td[5]/button
${Delete2}        xpath=//*[@id="row-2"]/td[5]/button
${Delete3}        xpath=//*[@id="row-3"]/td[5]/button
${ListAll}        xpath=/html/body/div/div/div[2]/button[1]
${ListCompleted}    xpath=/html/body/div/div/div[2]/button[2]
${checkbox1}      xpath=//*[@id="checkbox-1"]
${checkbox2}      xpath=//*[@id="checkbox-2"]
${checkbox3}      xpath=//*[@id="checkbox-3"]

*** Test Cases ***
Test case for add
    homepage
    ${"EnterToDo"}    set variable    xpath= //*[@id="todo"]
    ${"Describtion"}    set variable    xpath= //*[@id="desc"]
    element should be visible    ${"EnterToDo"}
    element should be enabled    ${"EnterToDo"}
    element should be visible    ${"Describtion"}
    element should be enabled    ${"Describtion"}
    input text    ${"EnterToDo"}    pray
    sleep    3
    input text    ${"Describtion"}    pray
    sleep    3
    click Element    ${Submit}
    sleep    3
    clear element text    ${"EnterToDo"}
    clear element text    ${"Describtion"}
    sleep    3
    element should be visible    ${"EnterToDo"}
    element should be enabled    ${"EnterToDo"}
    element should be visible    ${"Describtion"}
    element should be enabled    ${"Describtion"}
    input text    ${"EnterToDo"}    play
    sleep    3
    input text    ${"Describtion"}    play
    sleep    3
    click Element    ${Submit}
    sleep    3
    clear element text    ${"EnterToDo"}
    clear element text    ${"Describtion"}
    sleep    3
    element should be visible    ${"EnterToDo"}
    element should be enabled    ${"EnterToDo"}
    element should be visible    ${"Describtion"}
    element should be enabled    ${"Describtion"}
    input text    ${"EnterToDo"}    eat
    sleep    3
    input text    ${"Describtion"}    eat
    sleep    3
    click Element    ${Submit}
    sleep    3
    clear element text    ${"EnterToDo"}
    clear element text    ${"Describtion"}
    sleep    3
    element should be visible    ${"EnterToDo"}
    element should be enabled    ${"EnterToDo"}
    element should be visible    ${"Describtion"}
    element should be enabled    ${"Describtion"}
    sleep    3

TestCase for list and check
    click Element    ${checkbox1}
    sleep    3
    click Element    ${checkbox2}
    sleep    3
    click Element    ${ListCompleted}
    sleep    3
    click Element    ${ListAll}

TestCase for delete
    click Element    ${Delete1}
    sleep    3
    click Element    ${Delete2}
    sleep    3
    click Element    ${Delete3}
    sleep    3
    Close Browser

*** Keywords ***
homepage
    Open Browser    ${URL}    Chrome
    Maximize Browser Window
