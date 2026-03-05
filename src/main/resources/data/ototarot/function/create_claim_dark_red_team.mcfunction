# Makes OP, creates claim, revokes OP, gives claim, makes OP again, sets as owner, removes OP again. Ye this shit crazy af bro






execute as @s at @s run flan transferClaim ototarot.dark_red_team

execute as @s at @s run flan bypass

execute as @s at @s run flan group players add Owner @p

execute as @s at @s run flan bypass

clear @s ototarot:claim_generator 1

tellraw @s [{"text":"Acabas de crear la parcela de tu equipo. Esta se puede expandir usando el ","color":"gold"},{"text":"reclamador de terreno","color":"red"},{"text":" y usando bloques de parcela obtenibles en el lobby. Para añadir a tu compañero de equipo a la parcela, usa","color":"gold"},{"text":" /flan menu","color":"green","bold":true},{"bold":false,"text":" y, en el apartado de Permisos de grupos, añade su nombre en el grupo Owner.","color":"gold"}]
