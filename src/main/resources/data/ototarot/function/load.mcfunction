# Creating scoreboards
scoreboard objectives add check_first_login dummy
scoreboard objectives add lives_remaining dummy
scoreboard objectives add death_count deathCount
scoreboard objectives add death_trigger deathCount
scoreboard objectives add null dummy
scoreboard objectives add has_received_claimer dummy
scoreboard objectives add is_creating_claim dummy
scoreboard objectives add has_given_claim_blocks_yet dummy

#Little things so that the live counter and other things don't break
scoreboard players set dead_person lives_remaining 0
scoreboard players set null null 0 

# Tick
function ototarot:tick

# Stop time and make it move slower
gamerule doDaylightCycle false
function ototarot:change_day

# Flan stuff
execute unless score triggerer has_given_claim_blocks_yet <= null null unless score triggerer has_given_claim_blocks_yet >= null null run function ototarot:give_teams_blocks