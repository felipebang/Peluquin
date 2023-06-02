import { Ability, AbilityBuilder } from '@casl/ability';
import store from '../../store/redux.store';
import { getLoggedUser } from './auth.service';

// Defines how to detect object's type
function subjectName(item) {
	if (!item || typeof item === 'string') {
		return item;
	}
	return item.__type;
}

const ability = getLoggedUser()
	? new Ability(defineRulesFor(getLoggedUser().roles))
	: new Ability([], { subjectName });

let currentRoles;
store.subscribe(() => {
	const prevRoles = currentRoles;
	currentRoles = getLoggedUser() ? getLoggedUser().roles : undefined;
	if (currentRoles && prevRoles !== currentRoles) {
		ability.update(defineRulesFor(currentRoles));
	}
});

function defineRulesFor(roles) {
	const { can, rules } = AbilityBuilder.extract();
	if (roles) {
		roles.map(rol => {
			const currentRol = rol.authority;
			const moduloPermiso = currentRol.split('_')[1];
			const modulo = moduloPermiso.split('-')[0];
			const permiso = moduloPermiso.split('-')[1];
			if (modulo === 'ADM' && !permiso) {
				can('manage', 'all');
			} else if (permiso) {
				can(permiso, modulo);
			}
		});
	}
	return rules;
}

export default ability;
