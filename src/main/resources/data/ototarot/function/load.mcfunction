# Creating scoreboards
scoreboard objectives add check_first_login dummy
scoreboard objectives add lives_remaining dummy
scoreboard objectives add death_count deathCount
scoreboard objectives add death_trigger deathCount
scoreboard objectives add null dummy

#Little things so that the live counter and other things don't break
scoreboard players set dead_person lives_remaining 0
scoreboard players set null null 0 

# Tick
function ototarot:tick

# Stop time and make it move slower
gamerule doDaylightCycle false
function ototarot:change_day