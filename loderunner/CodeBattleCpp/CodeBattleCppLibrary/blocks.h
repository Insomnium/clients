#pragma once

#include <cstdint>

enum class blocks : uint16_t
{
	NONE = L' ',

	BRICK = L'#',
	PIT_FILL_1 = L'1',
	PIT_FILL_2 = L'2',
	PIT_FILL_3 = L'3',
	PIT_FILL_4 = L'4',
	UNDESTROYABLE_WALL = L'☼',

	DRILL_PIT = L'*',

	ENEMY_LADDER = L'Q',
	ENEMY_LEFT = L'«',
	ENEMY_RIGHT = L'»',
	ENEMY_PIPE_LEFT = L'<',
	ENEMY_PIPE_RIGHT = L'>',
	ENEMY_PIT = L'X',

	GOLD = L'$',

	HERO_DIE = L'Ѡ',
	HERO_DRILL_LEFT = L'Я',
	HERO_DRILL_RIGHT = L'R',
	HERO_LADDER = L'Y',
	HERO_LEFT = L'◄',
	HERO_RIGHT = L'►',
	HERO_FALL_LEFT = L']',
	HERO_FALL_RIGHT = L'[',
	HERO_PIPE_LEFT = L'{',
	HERO_PIPE_RIGHT = L'}',

	OTHER_HERO_DIE = L'Z',
	OTHER_HERO_LEFT = L')',
	OTHER_HERO_RIGHT = L'(',
	OTHER_HERO_LADDER = L'U',
	OTHER_HERO_PIPE_LEFT = L'Э',
	OTHER_HERO_PIPE_RIGHT = L'Є',

	LADDER = L'H',
	PIPE = L'~'
};
