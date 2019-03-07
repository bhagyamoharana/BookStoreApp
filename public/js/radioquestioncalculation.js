function SaveLikert(form){

			console.log("HI");

				var q1 = form.elements.q1, a = q1.length;
				var q2 = form.elements.q2, b = q2.length;
				var q3 = form.elements.q3, c = q3.length;
				var q4 = form.elements.q4, d = q4.length;
                var q5 = form.elements.q5, e = q5.length;
                var q6 = form.elements.q6, f = q6.length;
                var q7 = form.elements.q7, g = q7.length;
                var q8 = form.elements.q8, h = q8.length;
                var q9 = form.elements.q9, i = q9.length;
                var q10 = form.elements.q10, j = q10.length;
				var qa1,qa2,qa3,qa4,qa5,qa6,qa7,qa8,qa9,qa10;

				while (--a > -1){
					if(q1[a].checked){
						qa1 = q1[a].value;
						console.log("Answer1 is: "+qa1);
					}
				}

				while(--b > -1){
					if(q2[b].checked){
						qa2 = q2[b].value;
						console.log("Answer2 is: " +qa2);
					}
				}

				while(--c > -1){
					if(q3[c].checked){
						qa3 = q3[c].value;
						console.log("Answer3 is: " +qa3);
					}
				}

				while(--d > -1){
					if(q4[d].checked){
						qa4 = q4[d].value;
						console.log("Answer4 is " +qa4);
					}
				}

				while(--e > -1){
                					if(q5[e].checked){
                						qa5 = q5[e].value;
                						console.log("Answer5 is: " +qa5);
                					}
                				}

                while(--f > -1){
                                	if(q6[f].checked){
                                	  qa6 = q6[f].value;
                                	console.log("Answer6 is: " +qa6);
                                }
                                }

                while(--g > -1){
                                     if(q7[g].checked){
                                      qa7 = q7[g].value;
                                      console.log("Answer7 is: " +qa7);
                                     }
                                }
                while(--h > -1){
                                      if(q8[h].checked){
                                     qa8 = q8[h].value;
                                     console.log("Answer8 is: " +qa8);
                                 }
                                 }
                while(--i > -1){
                                      if(q9[i].checked){
                                       qa9 = q9[i].value;
                                      console.log("Answer9 is: " +qa9);
                                 }
                                 }
                while(--j > -1){
                					if(q10[j].checked){
                						 qa10 = q10[j].value;
                                       console.log("Answer10 is: " +qa10);
                					}
                				}


				var JsonString = "[Likert Results Go Here]";
				var r = jsRoutes.controllers.QuestionController.saveAnswers();
                $.ajax({url: r.url, type: r.type, contentType: "application/json", data: JsonString });
			}



